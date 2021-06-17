package com.xiaokaige.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * 线程缓存工具类
 *
 * @author panxuan
 * @date 2021/4/13 10:32
 */
@Slf4j
public class ThreadLocalUtils {

    private static final ThreadLocal<Long> UID_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<Locale> LOCALE_LOCAL = new ThreadLocal<>();

    private ThreadLocalUtils() {
    }

    /**
     * 将用户 ID 保存至 ThreadLocal 中
     *
     * @param uid 用户 ID
     */
    public static void saveUid(Long uid) {
        UID_LOCAL.set(uid);
    }

    /**
     * 将语言环境保存至 ThreadLocal 中
     *
     * @param locale 语言环境
     */
    public static void saveLocale(String locale) {
        LOCALE_LOCAL.set(getLocale(locale));
    }

    /**
     * 从 ThreadLocal 中获取用户 ID
     *
     * @return 用户 ID
     */
    public static Long getUid() {
        return UID_LOCAL.get();
    }

    /**
     * 从 ThreadLocal 中获取用户 ID
     *
     * @return 用户 ID
     */
    public static Locale getLocale() {
        return LOCALE_LOCAL.get();
    }

    /**
     * 手动清除 ThreadLocal 缓存
     * 防止内存泄漏
     */
    public static void clear() {
        UID_LOCAL.remove();
        LOCALE_LOCAL.remove();
    }

    /**
     * 根据语言环境标识获取 Locale
     *
     * @param locale 语言环境标识
     * @return Locale 语言环境
     */
    private static Locale getLocale(String locale) {
        if (StringUtils.isEmpty(locale)) {
            log.warn("locale is empty, use default local SIMPLIFIED_CHINESE");
            return Locale.SIMPLIFIED_CHINESE;
        }
        switch (locale) {
            case "zh_TW":
                return Locale.TRADITIONAL_CHINESE;
            case "zh_CN":
                return Locale.SIMPLIFIED_CHINESE;
            case "en_US":
                return Locale.US;
            default:
                log.warn("{} not found, use default local SIMPLIFIED_CHINESE", locale);
                break;
        }
        return Locale.SIMPLIFIED_CHINESE;
    }
}
