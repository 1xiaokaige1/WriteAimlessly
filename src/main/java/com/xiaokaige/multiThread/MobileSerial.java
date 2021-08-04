package com.xiaokaige.multiThread;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author chenYX
 * @date 2021/7/29 16:29
 * @description
 */
public class MobileSerial extends JsonSerializer<String> {
    private String formatPatternName = "mobile";
    private String pattern = "ddd***dddd";


    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            String mobile = formatMobile(value);
            jsonGenerator.writeString(mobile);
        } catch (IOException e) {
            jsonGenerator.writeString(value);
        }
    }


    public String formatMobile(String mobile){
        if (StringUtils.isNotBlank(mobile)){
            int length = pattern.length();
            int startIndex = -1;
            boolean startFlag = true;
            int endIndex = length ;
            boolean endFlag = true;
            char[] chars = pattern.toCharArray();
            do {
                if (startFlag && !Objects.equals(chars[++startIndex],'d')){
                    startFlag = false;
                }
                if (endFlag && !Objects.equals(chars[--endIndex],'d')){
                    endFlag = false;
                }

            }while (endIndex > 0 && startIndex < length && (startFlag || endFlag));
            char[] chars1 = mobile.toCharArray();
            for (int i = 0; i < chars1.length; i++) {
                if (i >= startIndex && i <= chars1.length-(length-endIndex)){
                    chars1[i] = '*';
                }
            }
            return String.valueOf(chars1);
        }
        return StringUtils.EMPTY;
    }







}