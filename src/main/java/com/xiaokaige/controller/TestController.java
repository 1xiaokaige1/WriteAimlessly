package com.xiaokaige.controller;

import com.xiaokaige.annotataion.EnableLogRecord;
import com.xiaokaige.annotataion.SwaggerNotes;
import com.xiaokaige.dao.StudentMapper;
import com.xiaokaige.enums.LoginSubCode;
import com.xiaokaige.enums.TestCodeEnum;
import com.xiaokaige.enums.TestSubCode;
import com.xiaokaige.queryParam.UserParam;
import com.xiaokaige.response.ResponseInfo;
import com.xiaokaige.service.StudentService;
import com.xiaokaige.vo.StudentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

/**
 * @author zengkai
 * @date 2021/6/15 11:00
 */
@RestController
@Validated
@Api(tags = "TestController", description = "测试接口")
public class TestController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @SwaggerNotes(subCodeType = TestSubCode.class, codeType = {"A001"}, tip = "测试接口用例")
    @ApiResponses(@ApiResponse(response = StudentVO.class, code = 200, message = "ok"))
    @PostMapping("/test")
    @EnableLogRecord(value = "测试接口用例")
    public ResponseInfo<StudentVO> test01(@Valid @RequestBody UserParam userParam) {
        StudentVO studentVO = studentMapper.findStudentByIdTwo(userParam.getUserId());
        return ResponseInfo.ok(studentVO, TestSubCode.TEST_SUB_CODE_ONE);
    }

    @SwaggerNotes(subCodeType = LoginSubCode.class, codeType = {"AC001"}, tip = "测试用户登录接口")
    @PostMapping("/login")
    public ResponseInfo<Void> testLogin(@Valid @RequestBody UserParam userParam) {
        StudentVO studentVO = studentMapper.findStudentByIdTwo(userParam.getUserId());
        if (studentVO != null) {
            return ResponseInfo.ok(LoginSubCode.LOGIN_SUCCESS_CODE);
        }
        return ResponseInfo.ok(LoginSubCode.LOGIN_FAILURE_CODE);
    }

    @GetMapping("/testuser/{param}")
    public ResponseInfo<StudentVO> testMPApi(@PathVariable("param") String param){
        StudentVO studentVO = studentService.findSpecialStudent(param);
        return ResponseInfo.ok(studentVO, TestCodeEnum.TEST_SUCCESS_CODE);
    }



}
