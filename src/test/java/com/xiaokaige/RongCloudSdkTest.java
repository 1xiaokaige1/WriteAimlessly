package com.xiaokaige;

import com.stl.util.STLJsonUtils;
import com.xiaokaige.service.GroupInfoService;
import com.xiaokaige.service.UserTokenService;
import com.xiaokaige.utils.HttpRequestUtils;
import com.xiaokaige.utils.IdUtil;
import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.messages.UserInfo;
import io.rong.methods.group.Group;
import io.rong.methods.group.gag.Gag;
import io.rong.methods.message._private.Private;
import io.rong.methods.user.User;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.methods.user.block.Block;
import io.rong.methods.user.chat.Ban;
import io.rong.methods.user.whitelist.Whitelist;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.message.GroupMessage;
import io.rong.models.message.PrivateMessage;
import io.rong.models.response.*;
import io.rong.models.user.BanListModel;
import io.rong.models.user.BanModel;
import io.rong.models.user.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zengkai
 * @date 2021/6/24 10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RongCloudSdkTest {

    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private GroupInfoService groupInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private HttpRequestUtils httpRequestUtils;

    /**
     * appKey
     */
    private static final String appKey = "x4vkb1qpxw1tk";
    /**
     * appSecret
     */
    private static final String appSecret = "ReUgbEyd95T";

    private RongCloud rongCloud;

    private User user;

    private Block block;

    private Blacklist blackList;

    private Whitelist whitelist;

    private Group group;

    private Gag gag;

    private Private _private;

    private Ban ban;

    @Before
    public void before() {
        rongCloud = RongCloud.getInstance(appKey, appSecret);
        user = rongCloud.user;
        block = new Block(appKey, appSecret, rongCloud);
        blackList = rongCloud.user.blackList;
        whitelist = new Whitelist(appKey, appSecret, rongCloud);
        group = rongCloud.group;
        gag = rongCloud.group.gag;
        _private = rongCloud.message.msgPrivate;
        ban = rongCloud.user.ban;
    }

    /**
     * 注册用户，获取token
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        String userId = IdUtil.getUUID();
        UserModel userModel = new UserModel()
                .setId(userId)
                .setName("test_002");
        TokenResult result = user.register(userModel);
        String token = result.getToken();
        userTokenService.insertUserToken(userId, token);
        System.out.println(token);
    }

    /**
     * 获取用户信息
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        UserModel userModel = new UserModel().setId("1e0f106d-1495-419f-aa08-22eda17452b8");
        UserResult userResult = user.get(userModel);
        System.out.println("userResult: " + userResult);
    }

    /**
     * 更新用户信息
     *
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        UserModel userModel = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505").setName("fuxin");
        Result result = user.update(userModel);
        System.out.println("result: " + result);
    }

    /**
     * 封禁用户
     *
     * @throws Exception
     */
    @Test
    public void test04() throws Exception {
        UserModel userModel = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505").setMinute(60);
        Result result = block.add(userModel);
        System.out.println("result: " + result);
    }

    /**
     * 获取封禁用户
     *
     * @throws Exception
     */
    @Test
    public void test05() throws Exception {
        BlockUserResult blockResult = (BlockUserResult) block.getList();
        System.out.println("blockResult: " + blockResult);
    }

    /**
     * 解除封禁用户
     *
     * @throws Exception
     */
    @Test
    public void test06() throws Exception {
        ResponseResult unblockResult = block.remove("d46cbbba-1066-48d9-bd58-423c7b6b9505");
        System.out.println("unblockResult: " + unblockResult);
    }

    /**
     * 添加用户到黑名单
     *
     * @throws Exception
     */
    @Test
    public void test07() throws Exception {
        List<String> userIds = new ArrayList<>();
        userIds.add("1e0f106d-1495-419f-aa08-22eda17452b8");
        userIds.add("6b7ace87-af89-4296-a5e9-b68bb15a736c");
        userIds.add("72f893ec-dfa5-4590-8947-6c43c3e3f1d2");
        UserModel[] blackArr = userIds.stream().map(id -> {
            UserModel userModel = new UserModel();
            userModel.setId(id);
            return userModel;
        }).toArray(UserModel[]::new);
        UserModel user = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505").setBlacklist(blackArr);
        Result result = blackList.add(user);
        System.out.println("result: " + result);
    }

    /**
     * 获取某个用户的用户黑名单列表
     *
     * @throws Exception
     */
    @Test
    public void test08() throws Exception {
        UserModel userModel = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505");
        BlackListResult blackList = this.blackList.getList(userModel);
        System.out.println("blackList: " + blackList);
    }


    /**
     * 移除用户黑名单中的用户
     *
     * @throws Exception
     */
    @Test
    public void test09() throws Exception {
        List<String> userIds = new ArrayList<>();
        userIds.add("1e0f106d-1495-419f-aa08-22eda17452b8");
        userIds.add("6b7ace87-af89-4296-a5e9-b68bb15a736c");
        UserModel[] blackArr = userIds.stream().map(id -> {
            UserModel userModel = new UserModel();
            userModel.setId(id);
            return userModel;
        }).toArray(UserModel[]::new);
        UserModel user = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505").setBlacklist(blackArr);
        Result result = blackList.remove(user);
        System.out.println("result: " + result);
    }

    /**
     * 添加用户白名单列表
     *
     * @throws Exception
     */
    @Test
    public void test10() throws Exception {
        List<String> userIds = new ArrayList<>();
        userIds.add("1e0f106d-1495-419f-aa08-22eda17452b8");
        userIds.add("6b7ace87-af89-4296-a5e9-b68bb15a736c");
        userIds.add("72f893ec-dfa5-4590-8947-6c43c3e3f1d2");
        UserModel[] whiteArr = userIds.stream().map(id -> {
            UserModel userModel = new UserModel();
            userModel.setId(id);
            return userModel;
        }).toArray(UserModel[]::new);
        UserModel userModel = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505").setWhitelist(whiteArr);
        Result result = whitelist.add(userModel);
        System.out.println("result: " + result);
    }

    /**
     * 获取用户白名单列表
     *
     * @throws Exception
     */
    @Test
    public void test11() throws Exception {
        UserModel userModel = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505");
        PWhiteListResult list = whitelist.getList(userModel);
        System.out.println("list: " + list);
    }

    /**
     * 删除用户白名单列表
     *
     * @throws Exception
     */
    @Test
    public void test12() throws Exception {
        List<String> userIds = new ArrayList<>();
        userIds.add("1e0f106d-1495-419f-aa08-22eda17452b8");
        userIds.add("6b7ace87-af89-4296-a5e9-b68bb15a736c");
        UserModel[] whiteArr = userIds
                .stream()
                .map(id -> new UserModel().setId(id))
                .toArray(UserModel[]::new);
        UserModel userModel = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505").setWhitelist(whiteArr);
        Result result = whitelist.remove(userModel);
        System.out.println("result: " + result);
    }

    /**
     * 创建群组
     *
     * @throws Exception
     */
    @Test
    public void test13() throws Exception {
        List<String> userIds = new ArrayList<>();
        userIds.add("1e0f106d-1495-419f-aa08-22eda17452b8");
        userIds.add("6b7ace87-af89-4296-a5e9-b68bb15a736c");
        userIds.add("72f893ec-dfa5-4590-8947-6c43c3e3f1d2");
        String groupId = IdUtil.getUUID();
        String groupName = "secret_garden";
        GroupMember[] groupMembers = userIds
                .stream()
                .map(id -> new GroupMember().setId(id))
                .toArray(GroupMember[]::new);
        GroupModel groupModel = new GroupModel()
                .setId(groupId)
                .setMembers(groupMembers)
                .setName(groupName);
        Result result = group.create(groupModel);
        groupInfoService.insertGroupInfo(groupId, groupName);
        System.out.println("result: " + result);
    }

    /**
     * 获取群组成员
     *
     * @throws Exception
     */
    @Test
    public void test14() throws Exception {
        GroupModel groupModel = new GroupModel().setId("47531bf5-0391-446b-aa02-f7d5306bcb4a");
        GroupUserQueryResult result = group.get(groupModel);
        System.out.println("result: " + result);
    }

    /**
     * 加入群组
     *
     * @throws Exception
     */
    @Test
    public void test15() throws Exception {
        GroupMember[] groupMembers = {new GroupMember().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505")};
        GroupModel groupModel = new GroupModel().setId("47531bf5-0391-446b-aa02-f7d5306bcb4a").setMembers(groupMembers).setName("secret_garden");
        Result result = group.join(groupModel);
        System.out.println("result: " + result);
    }

    /**
     * 退出群组
     *
     * @throws Exception
     */
    @Test
    public void test16() throws Exception {
        GroupMember[] groupMembers = {new GroupMember().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505")};
        GroupModel groupModel = new GroupModel().setId("47531bf5-0391-446b-aa02-f7d5306bcb4a").setMembers(groupMembers).setName("secret_garden");
        Result result = group.quit(groupModel);
        System.out.println("result: " + result);
    }

    /**
     * 邀请用户加入群组
     *
     * @throws Exception
     */
    @Test
    public void test17() throws Exception {
        List<String> userIds = new ArrayList<>();
        userIds.add("1e0f106d-1495-419f-aa08-22eda17452b8");
        userIds.add("6b7ace87-af89-4296-a5e9-b68bb15a736c");
        userIds.add("72f893ec-dfa5-4590-8947-6c43c3e3f1d2");
        userIds.add("d46cbbba-1066-48d9-bd58-423c7b6b9505");
        GroupMember[] groupMembers = userIds
                .stream()
                .map(id -> new GroupMember().setId(id))
                .toArray(GroupMember[]::new);
        //GroupMember[] groupMembers = {new GroupMember().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505")};
        GroupModel groupModel = new GroupModel().setId("47531bf5-0391-446b-aa02-f7d5306bcb4a").setName("happy_garden").setMembers(groupMembers);
        Result result = group.invite(groupModel);
        System.out.println("result: " + result);
    }

    /**
     * 解散群组
     *
     * @throws Exception
     */
    @Test
    public void test18() throws Exception {
        GroupMember[] groupMembers = {new GroupMember().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505")};
        GroupModel groupModel = new GroupModel().setId("47531bf5-0391-446b-aa02-f7d5306bcb4a").setName("secret_garden").setMembers(groupMembers);
        Result result = group.dismiss(groupModel);
        System.out.println("result: " + result);
    }

    /**
     * 更新群组信息
     *
     * @throws Exception
     */
    @Test
    public void test19() throws Exception {
        String groupId = "47531bf5-0391-446b-aa02-f7d5306bcb4a";
        String groupName = "happy_garden";
        GroupModel groupModel = new GroupModel().setId(groupId).setName(groupName);
        Result result = group.update(groupModel);
        groupInfoService.updateGroupInfo(groupId, groupName);
        System.out.println("result: " + result);
    }

    /**
     * 禁言群成员
     *
     * @throws Exception
     */
    @Test
    public void test20() throws Exception {
        List<String> userIds = new ArrayList<>();
        userIds.add("1e0f106d-1495-419f-aa08-22eda17452b8");
        userIds.add("6b7ace87-af89-4296-a5e9-b68bb15a736c");
        GroupMember[] groupMembers = userIds
                .stream()
                .map(userId -> new GroupMember().setId(userId))
                .toArray(GroupMember[]::new);
        GroupModel groupModel = new GroupModel().setId("47531bf5-0391-446b-aa02-f7d5306bcb4a").setMembers(groupMembers).setMinute(60);
        Result result = gag.add(groupModel);
        System.out.println("result: " + result);
    }

    /**
     * 查询群组被禁言用户
     *
     * @throws Exception
     */
    @Test
    public void test21() throws Exception {
        ListGagGroupUserResult resultList = gag.getList("47531bf5-0391-446b-aa02-f7d5306bcb4a");
        System.out.println("resultList: " + resultList);
    }

    /**
     * 发送消息
     *
     * @throws Exception
     */
    @Test
    public void test22() throws Exception {
        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setSenderId("1e0f106d-1495-419f-aa08-22eda17452b8");
        groupMessage.setTargetId(new String[]{"47531bf5-0391-446b-aa02-f7d5306bcb4a"});
        groupMessage.setObjectName("RC:TxtMsg");
        UserInfo userInfo = new UserInfo("1e0f106d-1495-419f-aa08-22eda17452b8", "yangkeke", "http://www.rongcloud.cn/images/logo.png", "");
        TxtMessage txtMessage = new TxtMessage("hello", "", userInfo);
        groupMessage.setContent(txtMessage);
        io.rong.methods.message.group.Group group = new io.rong.methods.message.group.Group(appKey, appSecret);
        group.setRongCloud(rongCloud);
        ResponseResult result = group.send(groupMessage);
        System.out.println("result: " + result);
    }

    @Test
    public void test23() {
        /*Map<String, List<String>> map = new HashMap<>();
        List<String> listOne = new ArrayList<>();
        listOne.add("1");
        listOne.add("2");
        map.put("one", listOne);
        redisTemplate.opsForHash().putAll("userOne", map);
        Map<String,List<String>> mapResult = redisTemplate.opsForHash().entries("userOne");
        listOne.add("3");
        redisTemplate.opsForHash().put("userOne","one",listOne);*/

        List<String> listTwo = new ArrayList<>();
        listTwo.add("666");
        //redisTemplate.opsForHash().put("userOne","one",listTwo);
        redisTemplate.opsForHash().delete("userOne", "one");
        //System.out.println(mapResult);
    }

    @Test
    public void test24() throws Exception {

        UserInfo userInfo = new UserInfo();
        userInfo.setId("68222fed-6c12-48bb-9ac9-6ad4f335412e");
        userInfo.setName("test_001");
        userInfo.setIcon("");
        userInfo.setExtra("");

        TxtMessage txtMessage = new TxtMessage("hello world", "", userInfo);

        PrivateMessage privateMessage = new PrivateMessage()
                .setSenderId("68222fed-6c12-48bb-9ac9-6ad4f335412e")
                .setTargetId(new String[]{"39fc12fc-0314-4ded-b714-77ebad32cfc6"})
                .setObjectName("RC:TxtMsg")
                .setContent(txtMessage);

        ResponseResult result = _private.send(privateMessage);
        System.out.println(result);
    }

    @Test
    public void test25() throws Exception {
        BanModel banModel = new BanModel();
        banModel.setState(1)
                .setUserId(new String[]{"68222fed-6c12-48bb-9ac9-6ad4f335412e"})
                .setType("PERSON");
        Result result = ban.set(banModel);
        System.out.println(result);


    }


    @Test
    public void test26() throws Exception {
        BanListModel banListModel = new BanListModel();
        banListModel.setNum(100).setOffset(0).setType("PERSON");
        BanListResult list = ban.getList(banListModel);
        System.out.println(list);
    }

    @Test
    public void test006() {
        UserLangDTO userLangDTO = new UserLangDTO();
        userLangDTO.setCloudUserId("612706558987212527");
        userLangDTO.setLang(1);
        userLangDTO.setUserId(1007245L);
        redisTemplate.opsForHash().put("user_lang", "1007245", STLJsonUtils.obj2json(userLangDTO));
    }

    @Test
    public void test007() throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        map.put("uid", "16792");
        map.put("swagger", "1");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String acquireResponse = httpRequestUtils.getWithHeader("http://192.168.1.56:8030/api/testAcquire", null, map);
                String releaseResponse = httpRequestUtils.postWithHeader("http://192.168.1.56:8030/api/releaseAcquire", null, map);
                System.out.println("acquireResponse = " + acquireResponse);
                System.out.println("releaseResponse = " + releaseResponse);
            }).start();
        }
        Thread.sleep(10000);
    }


}
