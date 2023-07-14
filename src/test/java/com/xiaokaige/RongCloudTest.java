package com.xiaokaige;

import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.CheckOnlineResult;
import io.rong.models.response.GroupUserQueryResult;
import io.rong.models.response.TokenResult;
import io.rong.models.response.UserResult;
import io.rong.models.user.UserModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengkai
 * @date 2021/6/21 11:24
 */
public class RongCloudTest {

    private String appKey = "x18ywvqfx4vec";
    private String appSecret = "DaG1zM3RjJ";

    RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    private User user;

    @Before
    public void before(){
        user = rongCloud.user;
    }

    @Test
    public void test01() throws Exception {
        UserModel userModel = new UserModel()
                .setId("002")
                .setName("xiaokaige2")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = user.register(userModel);
        System.out.println("getToken:  " + result.toString());
    }

    @Test
    public void test02() throws Exception {
        UserModel userModel = new UserModel()
                .setId("002")
                .setName("xiaokaige2")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        UserResult userResult = user.get(userModel);
        System.out.println(userResult);
    }

    @Test
    public void test03() throws Exception {
        UserModel user = new UserModel();
        user.setId("002");
        CheckOnlineResult result = rongCloud.user.onlineStatus.check(user);
        System.out.println("checkOnline:  " + result.toString());
    }

    @Test
    public void test04() throws Exception {
        UserModel userModel = new UserModel()
                .setId("kyy_yh_1")
                .setName("用户1号")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        UserResult userResult = user.get(userModel);
        System.out.println(userResult);
    }

    @Test
    public void tes05() throws Exception {
        Group Group = rongCloud.group;
        List<GroupMember> list = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        ids.add("abc");
        ids.add("bcd");
        ids.forEach(id->{
            GroupMember groupMember = new GroupMember();
            groupMember.setId(id);
            list.add(groupMember);
        });
        GroupMember[] groupMembers = list.toArray(new GroupMember[0]);

        GroupModel groupCreate = new GroupModel()
                .setId("001G")
                .setMembers(groupMembers)
                .setName("first_group");
        Result groupCreateResult = (Result)Group.create(groupCreate);
        System.out.println(groupCreateResult);
    }

    @Test
    public void test06() throws Exception {
        Group Group = rongCloud.group;
        GroupModel groupCreate = new GroupModel()
                .setId("001G");
        GroupUserQueryResult result = Group.get(groupCreate);
        System.out.println(result);
    }

    @Test
    public void test07() throws Exception {
        for (int i = 3; i < 99; i++) {
            UserModel userModel = new UserModel()
                    .setId("00"+i)
                    .setName("xiaokaige"+i)
                    .setPortrait("http://www.rongcloud.cn/images/logo.png");
            TokenResult result = user.register(userModel);
        }
    }

    @Test
    public void test08() throws Exception {
        UserModel userModel = new UserModel()
                .setId("1000")
                .setName("xiaokaige1000")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = user.register(userModel);
        System.out.println(result);
    }

    @Test
    public void test009(){

    }




}
