package com.wfj.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangxuan on 2016-11-25 0025.
 */
public class TestMemberPointInfoServiceImpl {

    public static void main(String args[]) throws Exception {
        test();
//        getNowDateShort();
    }

    public static void test() throws ParseException {
        Date pointTime = new Date();
        System.out.println(pointTime);
        System.out.println(pointTime.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(pointTime);
        System.out.println(format);
        Date date = sdf.parse(format);
        System.out.println(date);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        System.out.println(currentTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        System.out.println(currentTime_2);
        return currentTime_2;
    }

}
