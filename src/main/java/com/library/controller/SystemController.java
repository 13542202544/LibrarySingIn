package com.library.controller;

import com.library.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by mobk on 2015/11/28.
 */
@Controller
@RequestMapping("/System")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @ResponseBody
    @RequestMapping("/findDutyEmp")
    public String findDutyEmp(){
        return systemService.findDutyEmp(new Date());
    }

    @ResponseBody
    @RequestMapping("/findEndDutyEmp")
    public String findEndDutyEmp(){
        return systemService.findEndDutyEmp(new Date());
    }
}
