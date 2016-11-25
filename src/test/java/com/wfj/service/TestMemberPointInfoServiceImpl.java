package com.wfj.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangxuan on 2016-11-25 0025.
 */
public class TestMemberPointInfoServiceImpl {

    public static void main(String args[]) throws Exception {
        test();
    }

    public static void test() throws ParseException {
        Date pointTime = new Date();
        System.out.println(pointTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(pointTime);
        Date date = sdf.parse(format);
        System.out.println(date);
    }

}
