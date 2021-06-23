package com.xiaokaige.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaokaige.annotataion.EnableLogRecord;
import com.xiaokaige.entity.SystemLogDO;
import com.xiaokaige.service.SystemLogService;
import com.xiaokaige.utils.UrlUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
public class WebLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    @Autowired
    private SystemLogService systemLogService;

    //ThreadLocal<Long> startTime = new ThreadLocal();

    public WebLogAspect() {
        if (logger.isDebugEnabled()) {
            logger.debug("初始化日志切面.....");
        }

    }

    @Pointcut("@annotation(com.xiaokaige.annotataion.EnableLogRecord)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("WebLogAspect.doBefore()");
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long endTime = System.currentTimeMillis();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        EnableLogRecord annotation = targetMethod.getAnnotation(EnableLogRecord.class);
        if (annotation != null) {
            try {
                String methodnotes = annotation.value();
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                String url = request.getRequestURL().toString();
                String http_method = request.getMethod();
                String ip = request.getRemoteAddr();
                String class_method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
                Object[] allArgs = pjp.getArgs();
                Stream<Object> stream = ArrayUtils.isEmpty(allArgs) ? Stream.empty() : Arrays.stream(allArgs);
                List<Object> logArgs = stream
                        .filter(arg -> (!(arg instanceof MultipartFile)))
                        .collect(Collectors.toList());
                String args = JSONObject.toJSONString(logArgs);

                System.out.println("测试一下");
                String userId = request.getHeader("loginUserId");
                String userName = UrlUtil.getURLDecoderString(request.getHeader("loginUserName"));
                long time = endTime - startTime;
                if (logger.isDebugEnabled()) {
                    logger.debug("URL : " + url);
                    logger.debug("HTTP_Method : " + http_method);
                    logger.debug("IP : " + ip);
                    logger.debug("Class_Method : " + class_method);
                    logger.debug("Args : " + args);
                    logger.debug("Method Notes ：" + methodnotes);
                    logger.debug("Request User Id　：" + userId);
                    logger.debug("Request User Name　：" + userName);
                    logger.debug("process result　：" + JSON.toJSONString(obj));
                    logger.debug("process time: " + time + " ms");
                }

                Date now = new Date();
                SystemLogDO log = new SystemLogDO(ip, methodnotes, userId, userName, url, now, http_method, JSON.toJSONString(obj), class_method, args, time);
                log.setTenantId(1L);
                systemLogService.save(log);
            } catch (Exception var26) {
                logger.error(var26.getMessage(), var26);
            }
        }

        return obj;
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        logger.info("WebLogAspect.doAfterReturning()");
    }

    @AfterThrowing("webLog()")
    public void doAfterThrowing() {
        logger.info("WebLogAspect.doAfterThrowing()");
    }
}
