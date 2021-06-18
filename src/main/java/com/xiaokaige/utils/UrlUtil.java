package com.xiaokaige.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtil {
    private static final String ENCODE = "UTF-8";

    public UrlUtil() {
    }

    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        } else {
            try {
                result = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
            }

            return result;
        }
    }

    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        } else {
            try {
                result = URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
            }

            return result;
        }
    }
}
