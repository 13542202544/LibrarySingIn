package com.library.controller;

import com.library.service.SingInServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by mobk on 2015/11/28.
 */
@Controller
@RequestMapping("/SingIn")
public class SingInController {

    @Autowired
    private SingInServer singInServer;

    @ResponseBody
    @RequestMapping("startTime")
    private boolean startTime(String userID, int con, String nost){
        if(singInServer.startTime(userID, con, nost, null)){
            System.out.println(userID + con + nost);
            return true;
        }else{
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("endTime")
    private boolean endTime(String userID, int con, String nost){
        if(singInServer.endTime(userID, con, nost)){
            return true;
        }else{
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("replaceCheck")
    public String replaceCheck(String beJobId, int con, String beNumber, String jobId, String number, String nost) {
        if (!singInServer.login(beJobId, beNumber)) {
            return "beFalse";
        }
        if (!singInServer.login(jobId, number)) {
            return "false";
        }
        if(singInServer.startTime(jobId, con, nost, beJobId)){
            return "true";
        }else{
            return "false";
        }
    }

    @RequestMapping("replace")
    public String replace(String id, String con, Map<String, Object> map) {
        map.put("id", id);
        map.put("con", con);
        return "SingIn/replace";
    }

    @RequestMapping(value = "temporary", method = RequestMethod.GET)
    public String temporary(String con, Map<String,Object> map) {
        map.put("con", con);
        System.out.println("temporary");
        return "SingIn/temporary";
    }

    @ResponseBody
    @RequestMapping(value = "temporary", method = RequestMethod.POST)
    public String temporaryPost(Integer con, String jobId, String number, String nost) {
        if (singInServer.login(jobId, number)) {
            if (singInServer.startTime(jobId, con, nost, null)) {
                return "true";
            }else{
                return "请确认你目前不在值班后再试!";
            }
        }else{
            return "请确认工号与学号无误后再试!";
        }

    }
}
