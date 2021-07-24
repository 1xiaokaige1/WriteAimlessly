package com.xiaokaige.rongcloud;

import com.xiaokaige.config.RongConfig;
import io.rong.RongCloud;
import io.rong.methods.message._private.Private;
import io.rong.methods.message.system.MsgSystem;
import io.rong.methods.user.User;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.models.Result;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zengkai
 * @date 2021/7/2 8:55
 */
@Service
public class RongClientImpl implements RongClient {
    @Autowired
    private RongConfig rongConfig;

    private RongCloud rongCloud;

    private User user;

    private Blacklist blacklist;

    private MsgSystem system;

    private Private _private;

    @PostConstruct
    public void postConstruct() {
        rongCloud = RongCloud.getInstance(rongConfig.getAppKey(), rongConfig.getAppSecret());
        user = rongCloud.user;
        blacklist = rongCloud.user.blackList;
        _private = rongCloud.message.msgPrivate;
        system = rongCloud.message.system;
    }

    @Override
    public TokenResult register(String id, String name, String portrait) throws Exception {
        UserModel userModel = new UserModel()
                .setId(id)
                .setName(name)
                .setPortrait(portrait);
        return user.register(userModel);
    }

    @Override
    public Result updateUser(String id, String name, String portrait) throws Exception {
        UserModel userModel = new UserModel()
                .setId(id)
                .setName(name)
                .setPortrait(portrait);
        return user.update(userModel);
    }


}
