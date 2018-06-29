package com.library.controller;

import com.google.gson.GsonBuilder;
import com.library.dao.daoImp.Pager;
import com.library.model.Employee;
import com.library.model.Graded;
import com.library.model.GradedItem;
import com.library.model.InterviewSI;
import com.library.service.AdminService;
import com.library.service.InterviewService;
import com.library.tool.Excel;
import com.library.tool.MobileIf;
import com.library.tool.NewExcel;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by LinTi on 2016/9/29.
 */
@Controller
@RequestMapping("/Interview")
public class InterviewController extends BaseController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private AdminService adminService;

    /**
     * 主页
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        return "/Interview/interviewIndex" + getm(request);
    }

    /**
     * 查看评分列
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER')")
    @RequestMapping("/showGradItem")
    public String showGradItem(Map<String, Object> map) {
        map.put("gradeds", interviewService.getGradedItem());
        return "/Interview/showGradItem";
    }

    /**
     * 评分页面
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/getIntEmp", method = RequestMethod.POST)
    public String graded(HttpServletRequest request, Map<String, Object> map, String number) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        InterviewSI interviewSI = interviewService.getInterviewSI(number);
        if (interviewSI != null) {
            map.put("gradItem", interviewService.getGradedItem());
            map.put("interSI", interviewSI);
            map.put("user", interviewService.getEmployeeById(name));
            return "/Interview/graded" + getm(request);
        } else {
            return "/Interview/getIntEmp" + getm(request);
        }
    }

    /**
     * 签到
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/SI", method = RequestMethod.GET)
    public String interviewSi() {
        return "/Interview/SI";
    }

    /**
     * 获取面试者页面
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/getIntEmp", method = RequestMethod.GET)
    public String getIntEmp(HttpServletRequest request) {
        return "/Interview/getIntEmp" + getm(request);
    }

    /**
     * 添加评分列页面
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/addGradItem", method = RequestMethod.GET)
    public String addGradItem() {
        return "/Interview/addGradItem";
    }

    /**
     * 添加评分列
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER')")
    @RequestMapping(value = "/addGradItem", method = RequestMethod.POST)
    public String saveGeadItem(String[] itemName, int[] maxScore, int[] id) throws Exception {
        if (itemName.length != maxScore.length) throw new Exception("长度不一样");
        System.out.println(id == null);
        if (id != null && itemName.length != id.length) throw new Exception("长度不一样");
        List<GradedItem> gradedItems = new ArrayList<GradedItem>();
        for (int i = 0; i < itemName.length; i++) {
            GradedItem gi = new GradedItem();
            if (id != null) {
                gi.setId(id[i]);
            }
            gi.setItemName(itemName[i]);
            gi.setMaxScore(maxScore[i]);
            gradedItems.add(gi);
        }
        if (!interviewService.setGradItem(gradedItems)) throw new Exception("评分列表保存错误");
        return "redirect:/Interview/showGradItem";
    }

    /**
     * 删除评分列
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER')")
    @RequestMapping(value = "/delGradItem", method = RequestMethod.POST)
    public boolean delGradItem(int[] id) {
        return interviewService.delGradItem(id);
    }

    /**
     * 评分添加
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/graded", method = RequestMethod.POST)
    public String setGraded(HttpServletRequest request, int[] itemId, int[] score, String empId, String userId, String note) throws Exception {
        List<Graded> gradeds = new ArrayList<Graded>();
        if (itemId.length != score.length) throw new Exception("长度不一样");
        Employee employee = new Employee();
        employee.seteId(empId);
        Employee interviewer = new Employee();
        interviewer.seteId(userId);
        for (int i = 0; i < score.length; i++) {
            Graded graded = new Graded();
            graded.setGradedItemName(itemId[i] + "");
            graded.setScore(score[i]);
            graded.setEmployee(employee);
            graded.setInterviewer(interviewer);
            gradeds.add(graded);
        }
        if (gradeds.size() > 0) {
            gradeds.get(0).setNote(note);
        }
        interviewService.graded(gradeds);
        return "redirect:/Interview/getIntEmp";
    }

    /**
     * 根据电话获取人
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/getEmp", method = RequestMethod.POST)
    public String getEmp(String phone, Map<String, Object> map) {
        List<Employee> e = interviewService.getEmpByPhone(phone);
        return new GsonBuilder()
                .setDateFormat("HH:mm:ss")
                .create()
                .toJson(e);
    }

    /**
     * 签到返回编码
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/SI", method = RequestMethod.POST)
    public String getNum(String eId, String email) {
        String num = interviewService.si(eId, email);
        return num + "";
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/SI1", method = RequestMethod.POST)
    public String getNum1(@Valid Employee employee, Errors errors, Map<String, Object> map) {
        String num = interviewService.si(employee.geteId(), employee.geteEmail());
        Map<String, String> errorsMap = new HashMap<String, String>();
        for (ObjectError l : errors.getAllErrors()) {
            //错误信息
            String errorMes = "l.getDefaultMessage()" + l.getDefaultMessage();
            //第一个
            DefaultMessageSourceResolvable defaultMessageSourceResolvable = (DefaultMessageSourceResolvable) l.getArguments()[0];
            //获取异常的属性
            String errorProp = "defaultMessageSourceResolvable.getCode()" + defaultMessageSourceResolvable.getCode();
            errorsMap.put(errorMes,errorProp);
        }
        map.put("error",errorsMap);
        if (errorsMap.get("eEmail") != null) {
            return "-1";
        } else {
            return num + "";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER')")
    @RequestMapping("/outSI")
    public String outEmployee(Map<String, Object> map) throws IOException {
        String s[] = {"1"};
        List<InterviewSI> interviewSI = interviewService.getInterviewSIList();
        String path = System.getProperty("evan.webapp") + "File" + File.separator;
        String name = "SI" + new Date().getTime() + ".xls";
        String[] topLine = {"工号","姓名","性别","部门","联系方式","学号","系别","班级","邮箱","签到编号"};
        String[][] content = new String[interviewSI.size()][10];
        int i=0;
        for (InterviewSI isi:interviewSI) {
            Employee employee = isi.getEmployee();
            content[i][0] = employee.geteId();
            content[i][1] = employee.geteName();
            if (employee.geteSex() == '1') {
                content[i][2] = "男";
            }else if (employee.geteSex() == '2') {
                content[i][2] = "女";
            }else {
                content[i][2] = "?";
            }
            content[i][3] = employee.geteDepartment().getdName();
            content[i][4] = employee.getePhone();
            content[i][5] = employee.geteNumber();
            content[i][6] = employee.geteXiBie().getXbName();
            content[i][7] = employee.geteClazz();
            content[i][8] = employee.geteEmail();
            content[i][9] = interviewService.getSI(employee.geteId()).getNumber();
            i++;
        }
        new Excel().createExcel((path + name), "员工表", topLine, content);
        map.put("fileName", name);
        return "/download";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping(value = "/outInterview")
    public String outInterview(Map<String, Object> map) {
        String path = System.getProperty("evan.webapp") + "File" + File.separator;
        String name = "interview" + new Date().getTime() + ".xls";
        List<InterviewSI> interviewSIs = interviewService.getInterviewSIList();
        Employee e = interviewSIs.get(0).getEmployee();
        e.setGradeds(interviewService.getGradedByEmp(e.geteId()));
        List<String> topList = new ArrayList<String>();
        topList.add("签到编号");
        topList.add("姓名");
        topList.add("性别");
        topList.add("班级");
        String iID = "";
        for (Graded g : e.getGradeds()) {
            if (iID.equals("")) {
                iID = g.getInterviewer().geteId();
            }
            if (iID.equals(g.getInterviewer().geteId())) {
                topList.add(g.getGradedItemName());
            }
        }
        topList.add("面试官");
        topList.add("备注");
        List<List<String>> con = new ArrayList<List<String>>();
        for (InterviewSI i : interviewSIs) {
            i.getEmployee().setGradeds(interviewService.getGradedByEmp(i.getEmployee().geteId()));
            StringBuffer s = new StringBuffer();
            for (Graded g : i.getEmployee().getGradeds()) {
                String userName = g.getInterviewer().geteId();
                if (s.indexOf(userName) < 0) {
                    s.append(userName + ",");
                }
            }
            for (String j : s.toString().split(",")) {
                String interviewerName = "";
                List<String> strings = new ArrayList<String>();
                strings.add(i.getNumber() + "");
                strings.add(i.getEmployee().geteName());
                if (i.getEmployee().geteSex() == '1') strings.add("男");
                else if (i.getEmployee().geteSex() == '2') strings.add("女");
                else strings.add("?");
                strings.add(i.getEmployee().geteClazz());
                String note = "";
                for (Graded g : i.getEmployee().getGradeds()) {
                    if (g.getInterviewer().geteId().equals(j)) {
                        strings.add(g.getScore() + "");
                        interviewerName = g.getInterviewer().geteName();
                        if (g.getNote() != null) {
                            note = note + g.getNote();
                        }
                    }
                }
                strings.add(interviewerName);
                strings.add(note);
                con.add(strings);
            }
        }

        new NewExcel().createExcel((path + name), "面试分数", topList, con);
        map.put("fileName", name);
        return "/download";
    }

}
