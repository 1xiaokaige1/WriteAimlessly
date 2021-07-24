package com.xiaokaige.controller;

import com.xiaokaige.enums.TestSubCode;
import com.xiaokaige.queryParam.UserParam;
import com.xiaokaige.response.ResponseInfo;
import com.xiaokaige.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zengkai
 * @date 2021/7/2 9:56
 */
@RestController
@RequestMapping("/user")
@Validated
public class RongUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getToken")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flag", value = "token是否失效,0：是，1：否", dataTypeClass = String.class, example = "0",
                    required = false)
    })
    public ResponseInfo<String> getToken(@RequestHeader("uid") String uid,
                                         @RequestParam(required = false) String portrait,
                                         @RequestParam String nickname,
                                         @RequestParam(value = "flag", required = false) String flag) {
        String token = userService.getToken(uid, nickname, portrait, flag);
        return ResponseInfo.ok(token, TestSubCode.TEST_SUB_CODE_ONE);
    }

    @PostMapping("/updateUser")
    public ResponseInfo<Void> updateUser(@RequestHeader("uid") String uid,
                                         @Valid @RequestBody UserParam userParam) {
        userService.updateUserInfo(uid, userParam);
        return ResponseInfo.ok(TestSubCode.TEST_SUB_CODE_TWO);
    }

    @GetMapping("/test")
    public ResponseInfo<?> testGetInfo(UserParam userParam) {
        return ResponseInfo.ok(TestSubCode.TEST_SUB_CODE_ONE);
    }

}
