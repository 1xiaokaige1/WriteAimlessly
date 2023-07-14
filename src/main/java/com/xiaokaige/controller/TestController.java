package com.xiaokaige.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stl.util.STLJsonUtils;
import com.xiaokaige.dao.FxUserMapper;
import com.xiaokaige.entity.SignalNoticeBO;
import com.xiaokaige.entity.UserInfo;
import com.xiaokaige.enums.TestSubCode;
import com.xiaokaige.kafka.KfkService;
import com.xiaokaige.queryParam.AddPushMessageParam;
import com.xiaokaige.response.ResponseInfo;
import com.xiaokaige.utils.HttpRequestUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengkai
 * @date 2021/6/15 11:00
 */
@RestController
@Validated
@Api(tags = "TestController", description = "测试接口")
@Slf4j
public class TestController {

    @Autowired
    private FxUserMapper fxUserMapper;
    @Autowired
    private KfkService kfkService;
    @Autowired
    private HttpRequestUtils httpRequestUtils;

    /*@Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisTemplate redisTemplate;

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

    @PostMapping("/clearCache")
    public void test(){
        redisTemplate.delete("FX_GroupVideoInfo");
    }

    public static void main(String[] args) {
        File image = new File("image");
        String path = image.getAbsolutePath();
        System.out.println("path = " + path);
    }*/

    @PostMapping("/updatePwd")
    public void updatePwd(@RequestParam Integer fxcode) {
        UserInfo fxUserDO = new UserInfo();
        fxUserDO.setFxCode(fxcode);
        fxUserDO.setPassword("9c659aff3aeb8148abe322db04fbf45c");
        fxUserDO.setDefaultPassword(1);
        fxUserMapper.update(fxUserDO, new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getFxCode, fxcode));
    }

    @PostMapping("/sendTradingSignal")
    public ResponseInfo<String> updatePwd(@RequestBody SignalNoticeBO signalNoticeBO) {
        kfkService.sendMsg("trading.account.service.signal.test", STLJsonUtils.obj2json(signalNoticeBO));
        return ResponseInfo.ok("发送成功，如果失败请不要找我，嘿嘿", TestSubCode.TEST_SUB_CODE_ONE);
    }

    @PostMapping("/addPushMessage")
    public ResponseInfo<String> addPushMessage() {
        Map<String, String> headParamMap = buildHeadParam();
        Date now = new Date(1664764200000L);
        for (int i = 1; i < 301; i++) {
            long sendTime = now.getTime() + i * 60 * 1000L;
            String sendTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(sendTime));
            Map<String, Object> bodyParamMap = buildBodyParam(sendTimeStr, i);
            String responseStr = httpRequestUtils.postWithHeader("https://admin.fastbull.live/hkt-api/web/push/pushNotice",
                    bodyParamMap, headParamMap);
            log.info("增加定时推送任务响应结果:{}", responseStr);
        }
        return null;
    }

    /**
     * 构建请求体参数
     *
     * @return
     */
    private Map<String, Object> buildBodyParam(String date, Integer count) {
        Map<String, Object> bodyParamMap = new HashMap<>();
        bodyParamMap.put("contentData", "");
        bodyParamMap.put("langId", 4);
        bodyParamMap.put("noticeType", 2);
        bodyParamMap.put("content", "العملات في سوق الفوركس" + count);
        bodyParamMap.put("contentType", 5);
        bodyParamMap.put("pushAll", false);
        bodyParamMap.put("receiveUids", Collections.singleton(329));
        bodyParamMap.put("sendUid", 0);
        bodyParamMap.put("operateUserName", "funengwen");
        bodyParamMap.put("title", "معايير اختيار الوسطاء" + count);
        bodyParamMap.put("sendTime", date);
        return bodyParamMap;
    }

    /**
     * 构建请求头参数
     *
     * @return
     */
    private Map<String, String> buildHeadParam() {
        Map<String, String> headParamMap = new HashMap<>();
        headParamMap.put("uid", "100185");
        headParamMap.put("timestamp", "1664689825000");
        headParamMap.put("random", "24368096");
        headParamMap.put("MD5Code", "c0963da51c225c55310598f4fe6ecbd7");
        headParamMap.put("userName", "funengwen");
        return headParamMap;
    }

}
