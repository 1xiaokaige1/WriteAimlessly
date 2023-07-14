package com.xiaokaige.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zk
 * Date: 2022/2/17
 * Time: 9:07
 */
public class FileFunc {
    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("D:\\ideaProject\\test\\src\\main\\resources\\test.html");
        Path path = file.toPath();
        File fileTwo = new File("D:\\ideaProject\\test\\src\\main\\resources\\application.yml");
        Path pathTwo = fileTwo.toPath();

        String time = "2022-02-14T08:00:00";
        String replaceStr = time.replace("T", " ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(replaceStr);
        long time1 = date.getTime();
        System.out.println("time1 = " + time1);
    }
}
