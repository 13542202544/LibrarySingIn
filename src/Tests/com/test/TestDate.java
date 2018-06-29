package com.test;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/5.
 */
public class TestDate {
    public static void main(String[] args) {
        Date date = new Date();//当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");//格式化对象
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.DATE, 10);//月份减一
        calendar.add(Calendar.MONDAY, 8);//月份减一
        System.out.println(sdf.format(calendar.getTime()));//输出格式化的日期
    }
}
