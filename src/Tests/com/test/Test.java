package com.test;


import com.library.model.Department;
import com.library.model.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mobk on 2016/3/28.
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "三大";
        System.out.println(s);
        System.out.println(new String(s.getBytes("gbk"),"utf-8"));
        System.out.println(new String(s.getBytes("gbk"),"iso-8859-1"));
        System.out.println(new String(s.getBytes("utf-8"),"iso-8859-1"));
        System.out.println(new String(s.getBytes("utf-8"),"gbk"));
        System.out.println(new String(s.getBytes("iso-8859-1"),"gbk"));
        System.out.println(new String(s.getBytes("iso-8859-1"),"utf-8"));
    }

    @org.junit.Test
    public void test(){
        System.out.println("123");
    }
}
