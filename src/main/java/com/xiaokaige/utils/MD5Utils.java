package com.xiaokaige.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author: qinrongjun
 * @date: 2020/4/13 2:58 下午
 */
public class MD5Utils {
    private final static Logger logger = LoggerFactory.getLogger(MD5Utils.class);
    public static String md5(String clearText)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("{}", e.getLocalizedMessage());
            return null;
        }
        md.update(clearText.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toLowerCase();
    }

    public static String md5(byte[] bytes) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("{}", e.getLocalizedMessage());
            return null;
        }
        md.update(bytes);
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toLowerCase();
    }

    /**
     * 判断用户密码是否正确
     * newpasswd 用户输入的密码
     * oldpasswd 正确密码
     */
    public static boolean checkPassword(String newpasswd,String oldpasswd){
        if(md5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        System.out.println("F07D10A5E0B0CE1768D404691B5D8403".equals(md5("HUIKETANG_forum_web6e8c645d91dc83e0ad00c6b5f1a5fe6d1642995270000").toUpperCase()));
        System.out.println(Objects.requireNonNull(MD5Utils.md5("12345678w".getBytes())).toUpperCase());
        System.out.println(DigestUtils.md5DigestAsHex("12345678w".getBytes()).toUpperCase());
        System.out.println(DigestUtils.md5DigestAsHex("F07D10A5E0B0CE1768D404691B5D8403".getBytes()));

        BigDecimal number = BigDecimal.ZERO;
        for (int i = 0; i < 3; i++) {
            number = number.add(BigDecimal.ONE);
        }
        System.out.println("number = " + number);
    }



}
