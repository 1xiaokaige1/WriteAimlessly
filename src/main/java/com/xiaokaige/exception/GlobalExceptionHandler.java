package com.xiaokaige.exception;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaokaige.enums.SubCodeEnum;
import com.xiaokaige.enums.SystemSubCode;
import com.xiaokaige.response.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * 全局异常处理
 *
 * @author lgs
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseInfo<?> businessExceptionHandler(BusinessException businessException) {
        businessException.printStackTrace();
        return errorResponse(businessException.getSubCodeEnum().getDesc(), businessException.getSubCodeEnum());
    }

    /**
     * 未知异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(Exception.class)
    public ResponseInfo<?> exceptionHandler(Exception exception) {
        exception.printStackTrace();
        return errorResponse("未知异常 @类型@：" + exception.getClass().toString(),
                SystemSubCode.UNKNOWN_EXCEPTION);
    }

    /**
     * 运行时异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseInfo<?> runtimeExceptionHandler(RuntimeException exception) {
        exception.printStackTrace();
        log.error(exception.getLocalizedMessage());
        return errorResponse("运行时异常：" + exception.getLocalizedMessage(),
                SystemSubCode.RUNTIME_EXCEPTION);
    }

    /**
     * 空指针异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseInfo<?> nullPointerExceptionHandler(NullPointerException exception) {
        log.error(exception.getLocalizedMessage());
        exception.printStackTrace();
        return errorResponse("-->>空指针异常信息：" + exception.getStackTrace()[0].toString(), SystemSubCode.NULL_POINTER_EXCEPTION);
    }

    /**
     * 类型转换异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(ClassCastException.class)
    public ResponseInfo<?> classCastExceptionHandler(ClassCastException exception) {
        exception.printStackTrace();
        log.error(exception.getLocalizedMessage());
        return errorResponse("类型转换异常：" + exception.getLocalizedMessage(), SystemSubCode.CLASS_CAST_EXCEPTION);
    }

    /**
     * IO异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(IOException.class)
    public ResponseInfo<?> ioExceptionHandler(IOException exception) {
        log.error(exception.getLocalizedMessage());
        return errorResponse("IO异常：" + exception.getLocalizedMessage(), SystemSubCode.IO_EXCEPTION);
    }

    /**
     * 数组越界异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseInfo<?> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException exception) {
        exception.printStackTrace();
        log.error(exception.getLocalizedMessage());
        return errorResponse("数组越界异常：" + exception.getLocalizedMessage(), SystemSubCode.INDEX_OUT_BOUND_EXCEPTION);
    }

    /**
     * 请求方法不支持
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseInfo<?> request405(HttpRequestMethodNotSupportedException exception) {
        log.error(exception.getLocalizedMessage());
        return errorResponse("请求方法不支持：" + exception.getLocalizedMessage(), SystemSubCode.METHOD_NOT_SUPPORTED_EXCEPTION);
    }


    /**
     * 请求参数格式不支持(content-type类型)
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseInfo<?> request406(HttpMediaTypeNotSupportedException exception) {
        log.error(exception.getLocalizedMessage());
        return errorResponse("请求参数格式不支持：" + exception.getLocalizedMessage(), SystemSubCode.MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION);
    }

    /**
     * 请求参数无效(1:)参数校验不通过  2:)参数读取失败 3:) 请求参数缺少) 4:)参数校验失败
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class, ConstraintViolationException.class})
    public ResponseInfo<?> requestParamNotValid(Exception exception) {
        exception.printStackTrace();
        log.error(exception.getLocalizedMessage());
        String msg = "请求参数无效";
        // 获取参数校验错误提示信息
        if (exception.getClass() == MethodArgumentNotValidException.class) {
            MethodArgumentNotValidException notValidException = (MethodArgumentNotValidException) exception;
            BindingResult result = notValidException.getBindingResult();
            if (result.hasErrors() && null != result.getFieldError()) {
                msg = result.getFieldError().getDefaultMessage();
            }
        }
        return errorResponse(msg + "：" + exception.getLocalizedMessage(), SystemSubCode.PARAM_EXCEPTION);
    }

    /**
     * 枚举值绑定异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(BindException.class)
    public ResponseInfo<?> paramBindException(Exception exception) {
        log.error(exception.getLocalizedMessage());
        String msg = ((BindException) exception).getAllErrors().get(0).getDefaultMessage();
        if (StringUtils.isBlank(msg)) {
            msg = "枚举参数错误！";
        }
        return errorResponse(msg + "：" + exception.getLocalizedMessage(), SystemSubCode.PARAM_EXCEPTION);
    }

    /**
     * 返回错误消息
     *
     * @param msg     信息
     * @param subCode 状态码
     * @return R
     */
    private ResponseInfo<?> errorResponse(String msg, SubCodeEnum subCode) {
        log.error(msg);
        return ResponseInfo.error(msg, subCode);
    }


    /**
     * 类型转换异常
     *
     * @param exception 异常信息
     * @return R
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseInfo<?> numberFormatException(Exception exception) {
        String msg = "类型转换错误！";
        log.error(exception.getLocalizedMessage());
        return errorResponse(msg + "：" + exception.getLocalizedMessage(), SystemSubCode.NUMBER_FORMAT_EXCEPTION);
    }


}
