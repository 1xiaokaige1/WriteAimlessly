package com.xiaokaige.controller;

import com.xiaokaige.response.ResponseInfo;
import com.xiaokaige.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zk
 * Date: 2022/1/21
 * Time: 14:52
 */
@RestController
public class ExceptionTestController {

    @Autowired
    private GroupInfoService groupInfoService;

    @GetMapping("/exception")
    public ResponseInfo<?> testException(){
        return groupInfoService.testException();
    }
}
