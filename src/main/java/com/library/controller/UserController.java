package com.library.controller;

import com.library.model.Employee;
import com.library.model.SingIn;
import com.library.model.Vacation;
import com.library.model.Work;
import com.library.service.UserService;
import com.library.tool.Arrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LinTi on 2016/7/15.
 */
@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 主页
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping("/index")
    public String index(Map<String,Object> map) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        List<Work> works = userService.fineWork(name);
        map.put("works", new Arrange().work(works));
        return "User/userIndex";
    }

    /**
     * 请假页面
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(value = "/vacation", method = RequestMethod.GET)
    public String vacation(Map<String,Object> map){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        List<Work> works = userService.fineWork(name);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        List<Map> maps = new ArrayList<Map>();
        for (int o = 0; o < works.size(); o++) {
            Work w = works.get(o);
            calendar.setTime(new Date());
            for (int i = 0; i < 4; i++) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", calendar.getTime().getTime() + ";" + w.getwId());
                m.put("value" , simpleDateFormat.format(calendar.getTime()) + "  " + w.getwWorkTime().getWtTime() + " " + w.getwWorkContent().getWcCon() );
                maps.add(m);
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
            }
        }
        map.put("works", maps);
        return "User/vacation";
    }

    /**
     * 请假
     * @param cause
     * @param work
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(value = "/vacation", method = RequestMethod.POST)
    public String vacation1(String cause,String work){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        Vacation vacation = new Vacation();
        vacation.setvCause(cause);
        vacation.setvDate(new Date(Long.parseLong(work.split(";")[0])));
        vacation.setvWork(userService.getWork(work.split(";")[1]));
        vacation.setvEmployee(userService.getEmployee(name));
        vacation.setvForm('c');
        userService.saveVacation(vacation);
        return "User/userIndex";
    }

    /**
     * 查看工资
     * @param map
     * @param month
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping("wage")
    public String wage(Map<String,Object> map, String month){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        if(month == "" || month == null) {
            SimpleDateFormat dd = new SimpleDateFormat("yyyyMM");
            month = dd.format(new Date());
        }
        List<SingIn> singInList = userService.wage(month,name);
        long l = 0;
        for (int i = 0; i < singInList.size(); i++) {
            l = l + singInList.get(i).getSiEndTime().getTime() - singInList.get(i).getSiStartTime().getTime();
            singInList.get(i).setSiNotes(((singInList.get(i).getSiEndTime().getTime() - singInList.get(i).getSiStartTime().getTime())/60000) + "");
        }
        map.put("singInList",singInList);
        map.put("totalTime", (l/1000/60));
        map.put("totalWages", (l/60000/60*6));
        return "User/wage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/applyNow", method = RequestMethod.GET)
    public String applyStop(Map<String,Object> map) {
        map.put("employee", new Employee());
        map.put("xibie", userService.getXiBieList());
        map.put("department", userService.getDepartmentList());
        return "User/apply_bak";
    }

    /**
     * 报名连接
     * @param map
     * @return
     */
    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public String apply(Map<String,Object> map) {
        map.put("employee", new Employee());
        map.put("xibie", userService.getXiBieList());
        map.put("department", userService.getDepartmentList());
        return "User/apply";
//        return "User/applyStop";
    }

    /**
     * 报名申请
     * @param map
     * @param employee
     * @return
     */
    //要获取错误信息的属性 要用@Valid 注明
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String apply2(Map<String,Object> map, @Valid Employee employee, Errors errors) {
        for (ObjectError l : errors.getAllErrors()) {
            //属于 那个属性(类)的异常
            System.out.println("l.getObjectName()" + l.getObjectName());
            //什么原因(那个注解名称)
            System.out.println("l.getCode()" +l.getCode());
            //错误信息
            System.out.println("l.getDefaultMessage()" + l.getDefaultMessage());
            //第一个
            DefaultMessageSourceResolvable defaultMessageSourceResolvable = (DefaultMessageSourceResolvable) l.getArguments()[0];
            //获取异常的属性
            System.out.println("defaultMessageSourceResolvable.getCode()"+defaultMessageSourceResolvable.getCode());
        }
        if(errors.getErrorCount() > 0){
            map.put("xibie", userService.getXiBieList());
            map.put("errors", errors.getAllErrors());
            map.put("department", userService.getDepartmentList());
            return "User/apply";
        }
        boolean b = userService.saveEmployee(employee);
        return "User/applyChick";
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/apply1")
    public String apply4(Map<String,Object> map, @Valid Employee employee, Errors errors) {
        if(errors.getErrorCount() > 0){
            return "false";
        }
        Employee employee1 = userService.selectEmp(employee.geteName(), employee.getePhone());
        if (employee1 != null){
            employee.seteId(employee1.geteId());
        }
        if (!userService.saveEmployee(employee)) {
           return "false";
        }
        return "true";
    }

    @RequestMapping("/apply2")
    public String chick(){
        return "User/applyChick";
    }
}