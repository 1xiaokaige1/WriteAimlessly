package com.xiaokaige.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xiaokaige.annotataion.I18n;
import com.xiaokaige.enums.SubCodeEnum;
import com.xiaokaige.enums.SystemSubCode;
import com.xiaokaige.utils.MessageUtils;
import com.xiaokaige.utils.ThreadLocalUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 标准返回类型
 *
 * @author lgs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseInfo<T> {

    /**
     * 程序正常
     */
    public static final Integer RESPONSE_SUCCESS = 0;

    /**
     * 程序异常
     */
    public static final Integer RESPONSE_FAILURE = -1;

    /**
     * 默认返回的提示消息
     */
    private static final String DEFAULT_OK_MESSAGE = "ok";

    /**
     * 请求正常时，code为0，异常为-1
     */
    private Integer code = 0;

    /**
     * 业务描述码，第1位表示相应的环境，第2～3位表示域名编号，第4～6位表示API编号，第7～8位表示业务描述
     */
    private String subCode = "00000000";

    /**
     * 请求结果的业务描述,成功时不设置
     */
    private String message;

    /**
     * 请求的响应数据
     */
    private T bodyMessage;


    public static <T> ResponseInfo<PageModel<T>> page(List<T> result,
                                                  Object attach,
                                                  long total,
                                                  long index,
                                                  long size,
                                                  SubCodeEnum subCodeEnum) {
        String fullSubCode = subCode(subCodeEnum);
        ResponseInfo<PageModel<T>> responseInfo = new ResponseInfo<>();
        PageModel<T> pageModel = new PageModel<>();
        pageModel.setData(result);
        pageModel.setTotal(total);
        pageModel.setAttach(attach);
        pageModel.setIndex(index);
        pageModel.setSize(size);
        String message = getMessage(DEFAULT_OK_MESSAGE, subCodeEnum);
        responseInfo.setMessage(message);
        responseInfo.code = RESPONSE_SUCCESS;
        responseInfo.bodyMessage = pageModel;
        responseInfo.subCode = fullSubCode;
        return responseInfo;
    }

    public static <T> ResponseInfo<PageModel<T>> page(IPage<T> pageResult,
                                                      Object attach,
                                                      SubCodeEnum subCodeEnum) {
        return page(pageResult.getRecords(), attach, pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize()
                , subCodeEnum);
    }

    public static <T> ResponseInfo<PageModel<T>> page(IPage<T> pageResult,
                                                      SubCodeEnum subCodeEnum) {
        return page(pageResult.getRecords(), null, pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize()
                , subCodeEnum);
    }

    public static <T> ResponseInfo<PageModel<T>> page(List<T> dataList,
                                                      Object attach,
                                                      IPage<?> pageResult,
                                                      SubCodeEnum subCodeEnum) {
        return page(dataList, attach, pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize()
                , subCodeEnum);
    }

    public static <T> ResponseInfo<PageModel<T>> page(List<T> dataList,
                                                      IPage<?> pageResult,
                                                      SubCodeEnum subCodeEnum) {
        return page(dataList, null, pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize()
                , subCodeEnum);
    }


    public static <T> ResponseInfo<T> ok(T object, String message, SubCodeEnum subCodeEnum) {
        ResponseInfo<T> responseInfo = ok(object, subCodeEnum);
        message = getMessage(message, subCodeEnum);
        responseInfo.setMessage(message);
        return responseInfo;
    }

    public static <T> ResponseInfo<T> ok(SubCodeEnum subCodeEnum) {
        return ok(null, subCodeEnum);
    }

    public static <T> ResponseInfo<T> ok(T bodyMessage, SubCodeEnum subCodeEnum) {
        String fullSubCode = subCode(subCodeEnum);
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.code = RESPONSE_SUCCESS;
        responseInfo.bodyMessage = bodyMessage;
        responseInfo.subCode = fullSubCode;
        String message = getMessage(DEFAULT_OK_MESSAGE, subCodeEnum);
        responseInfo.setMessage(message);
        return responseInfo;
    }

    public static <T> ResponseInfo<T> info(String message, SubCodeEnum subCodeEnum) {
        return commonResponse(RESPONSE_SUCCESS, message, subCodeEnum);
    }

    public static <T> ResponseInfo<T> error(String message, SubCodeEnum subCodeEnum) {
        return commonResponse(RESPONSE_FAILURE, message, subCodeEnum);

    }

    private static String subCode(SubCodeEnum subCodeEnum) {
        return subCodeEnum.getSubCode();
    }

    /**
     * 鉴权失败
     *
     * @param msg         失败信息
     * @param subCodeEnum 编码
     * @return R
     */
    public static <T> ResponseInfo<T> authError(String msg, SubCodeEnum subCodeEnum) {
        String fullSubCode = subCode(SystemSubCode.AUTHORIZATION_REQUEST_EXCEPTION);
        ResponseInfo<T> standardResponse = new ResponseInfo<>();
        standardResponse.code = RESPONSE_FAILURE;
        standardResponse.message = msg;
        standardResponse.subCode = fullSubCode;
        return standardResponse;
    }

    /**
     * 服务调用异常
     *
     * @return R
     */
    public static <T> ResponseInfo<T> serviceError() {
        String fullSubCode = subCode(SystemSubCode.SERVICE_CALL_ERROR);
        ResponseInfo<T> standardResponse = new ResponseInfo<>();
        standardResponse.code = RESPONSE_FAILURE;
        standardResponse.message = getMessage(SystemSubCode.SERVICE_CALL_ERROR);
        standardResponse.subCode = fullSubCode;
        return standardResponse;
    }


    /**
     * 获取提示信息
     *
     * @param defaultMessage 默认提示语
     * @param subCodeEnum    业务码
     * @return 提示信息
     */
    @SuppressWarnings({"rawtypes"})
    private static String getMessage(String defaultMessage, SubCodeEnum subCodeEnum) {
        Class<? extends SubCodeEnum> subCodeEnumClass = subCodeEnum.getClass();
        if (!subCodeEnumClass.isEnum()) {
            return defaultMessage;
        }
        String enumName = ((Enum) subCodeEnum).name();
        try {
            Field field = subCodeEnumClass.getField(enumName);
            if (field.isAnnotationPresent(I18n.class)) {
                I18n i18n = field.getAnnotation(I18n.class);
                String key = i18n.key();
                String[] params = i18n.params();
                // 如果需要传递参数，需要在 i18n 文件(如 messages_zh_TW.properties)中使用占位符形式
                // 比如 0 表示是第一个，对应参数中的第一个值，以此类推
                // 如 test param0 {0} param1 {1}
                return MessageUtils.getMsg(key, params, ThreadLocalUtils.getLocale());
            }
        } catch (NoSuchFieldException e) {
            return defaultMessage;
        }
        return defaultMessage;
    }

    /**
     * 获取提示信息
     *
     * @param subCodeEnum 业务码
     * @return 提示信息
     */
    private static String getMessage(SubCodeEnum subCodeEnum) {
        return getMessage(subCodeEnum.getDesc(), subCodeEnum);
    }

    /**
     * 公共响应
     *
     * @param code        返回码 0 成功 -1 失败
     * @param subCodeEnum 业务码
     * @param <T>         返回参数类型
     * @return 响应
     */
    public static <T> ResponseInfo<T> commonResponse(int code, SubCodeEnum subCodeEnum) {
        // 默认消息为 SubCodeEnum 的描述，如果指定了国际化则使用国际化提示语
        return commonResponse(code, subCodeEnum.getDesc(), subCodeEnum);
    }

    /**
     * 公共响应
     *
     * @param code        返回码 0 成功 -1 失败
     * @param message     消息
     * @param subCodeEnum 业务码
     * @param <T>         返回参数类型
     * @return 响应
     */
    public static <T> ResponseInfo<T> commonResponse(int code, String message, SubCodeEnum subCodeEnum) {
        String fullSubCode = subCode(subCodeEnum);
        // 如果指定了国际化则使用国际化提示语，未指定则使用默认消息
        message = getMessage(message, subCodeEnum);
        ResponseInfo<T> standardResponse = new ResponseInfo<>();
        standardResponse.code = code;
        standardResponse.message = message;
        standardResponse.subCode = fullSubCode;
        return standardResponse;
    }
}
