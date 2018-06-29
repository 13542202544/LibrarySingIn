package com.test;

import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/1/27.
 */
public class TestSQLTime {
    public static void main(String[] args) {
        String s = "14:65";
        Time sqltime = Time.valueOf("14:50");
        System.out.println(sqltime);
    }
}
