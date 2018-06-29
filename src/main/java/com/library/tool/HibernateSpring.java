package com.library.tool;

import com.library.model.DutyTime;
import com.library.service.AdminService;
import com.library.service.SystemService;
import com.library.service.TestService;
import com.library.service.UiUserService;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by mobk on 2015/11/29.
 */
public class HibernateSpring{

    ApplicationContext ctx;
    UiUserService uiUserService;
    AdminService adminService;
    SystemService systemService;
    TestService testService;

    public HibernateSpring() {
        this.ctx = new ClassPathXmlApplicationContext("Spring.xml");
        this.uiUserService = (UiUserService) ctx.getBean("uiUserService");
        this.adminService = (AdminService) ctx.getBean("adminService");
        this.systemService = (SystemService) ctx.getBean("systemService");
        this.testService = (TestService) ctx.getBean("testService");
    }

    public String startTime(String jobID, String stuID, String workContent, String notes){
        return uiUserService.startTime(jobID, stuID, workContent, notes);
    }

    public String endTime(String jobID, String stuID, String notes){
        return uiUserService.endTime(jobID, stuID, notes);
    }

    public void inportEmployee(String excelPath){
        adminService.importEmployee(excelPath);
    }

    public void outputSingIn(String path, String name){
        adminService.outputSingIn(path, name);
    }

    public void importWork(String path){
        adminService.importWork(path);
    }

    public void outputModelWork(String path){
        adminService.outputModelWork(path);
    }

    public void addDutyTime(DutyTime[] dutyTime){
        systemService.addDutyTime(dutyTime);
    }

    public void findDutyEmp(Date date) {
        systemService.findDutyEmp(date);
    }

    public Session SQLTest(){
        return testService.testSql();
    }

}
