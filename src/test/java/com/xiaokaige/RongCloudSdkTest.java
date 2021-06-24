package com.xiaokaige;

import com.xiaokaige.service.GroupInfoService;
import com.xiaokaige.service.UserTokenService;
import com.xiaokaige.utils.IdUtil;
import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.methods.user.User;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.methods.user.block.Block;
import io.rong.methods.user.whitelist.Whitelist;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.*;
import io.rong.models.user.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

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

    /**
     * appKey
     */
    private static final String appKey = "c9kqb3rdc6thj";
    /**
     * appSecret
     */
    private static final String appSecret = "OPxgxvq7ZqT3";

    private RongCloud rongCloud;

    private User user;

    private Block block;

    private Blacklist blackList;

    private Whitelist whitelist;

    private Group group;

    @Before
    public void before() {
        rongCloud = RongCloud.getInstance(appKey, appSecret);
        user = rongCloud.user;
        block = new Block(appKey, appSecret, rongCloud);
        blackList = rongCloud.user.blackList;
        whitelist = new Whitelist(appKey, appSecret, rongCloud);
        group = rongCloud.group;
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
                .setName("niujiahuan")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
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
        UserModel userModel = new UserModel().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505");
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
        GroupMember[] groupMembers = {new GroupMember().setId("d46cbbba-1066-48d9-bd58-423c7b6b9505")};
        GroupModel groupModel = new GroupModel().setId("47531bf5-0391-446b-aa02-f7d5306bcb4a").setName("secret_garden").setMembers(groupMembers);
        Result result = group.invite(groupModel);
        System.out.println("result: " + result);
    }

    /**
     * 解散群组
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
     * @throws Exception
     */
    @Test
    public void test19() throws Exception {
        String groupId = "47531bf5-0391-446b-aa02-f7d5306bcb4a";
        String groupName = "happy_garden";
        GroupModel groupModel = new GroupModel().setId(groupId).setName(groupName);
        Result result = group.update(groupModel);
        groupInfoService.updateGroupInfo(groupId,groupName);
        System.out.println("result: " + result);
    }



}
