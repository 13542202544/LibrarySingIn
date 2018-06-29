package com.library.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.library.model.Account;
import com.library.model.Employee;
import com.library.dao.daoImp.Pager;
import com.library.model.Status;
import com.library.model.User;
import com.library.tool.Excel;
import com.library.tool.Information;
import org.apache.commons.io.FileUtils;
import com.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by mobk on 2015/11/25.
 */
@Controller
@RequestMapping("/Admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    public static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    /**
     * 管理员主页
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN','ROLE_USER')")
    @RequestMapping("/")
    public String adminIndex(HttpServletRequest request) {
        return "Admin/adminIndex" + getm(request);
    }

    /**
     * 下载签到表
     * @return
     * @throws IOException
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/outSingIn")
    public ResponseEntity<byte[]> outSingIn() throws IOException {
        String path = System.getProperty("evan.webapp") + "File" + File.separator;
        String name = "签到表" + new Date().toString() + ".xls";
        adminService.outputSingIn(path, name);
        File file = new File(path + name);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String(name.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    /**
     * 查看员工
     * @param map
     * @param id
     * @param dep
     * @param xiBie
     * @param sex
     * @param year
     * @param clazz
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/fileEmployee")
    public String fileEmployee(Map<String, Object> map,String id, String[] dep, String[] xiBie,
                               String[] sex, String year, String clazz, String[] status,
                               String pagination, String count, HttpServletRequest request) {
        int pageNo = 1;
        int pageSize = 10;
        try {
            pageNo = Integer.parseInt(pagination);
            if (pageNo < 1 ) pageNo = 1;
        } catch (Exception e){}
        try {
            pageSize = Integer.parseInt(count);
            if (pageSize < 1 ) pageSize = 10;
        } catch (Exception e){}
        Pager<Employee> employeePagerImp = adminService.fileEmployee(id, dep, xiBie, sex, year, clazz, status, pageNo, pageSize);
        map.put("status",adminService.getStatusList());
        if (id != null) {
            map.put("employee", employeePagerImp.getResult().get(0));
            map.put("departments", adminService.findDepartment());
            map.put("xiBies", adminService.findXiBie());
            map.put("type", "show");
            return "Admin/updateEmp";
        }
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("data", employeePagerImp.getResult());
        map1.put("count", employeePagerImp.getRowCount());
        map1.put("code", 0);
        map1.put("msg", "");
        System.out.println(new Gson().toJson(map1));
        return new Gson().toJson(map1);
    }

    /**
     * 添加员工
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/addEmp")
    public String addEmp(Map<String, Object> map) {
        map.put("type", "add");
        map.put("employee", new Employee());
        map.put("departments", adminService.findDepartment());
        map.put("xiBies", adminService.findXiBie());
        return "Admin/updateEmp";
    }

    /**
     * 更新添加员工信息
     * @param employee
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/saveOrUpdateEmp")
    public String saveOrUpdateEmp(Employee employee) {
        adminService.saveOrUpdateEmp(employee);
        return "redirect:/Admin/fileEmployee?id=" + employee.geteId();
    }

    /**
     * 进入添加管理员页面
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @RequestMapping(value = "/addAdmin", method = RequestMethod.GET)
    public String addAdmin(Map<String, Object> map) {
        map.put("user", new User());
        return "Admin/addAdmin";
    }

    /**
     * 查看所有管理员
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/showAdmin")
    public String showAdmin(Map<String, Object> map){
        List<User> userList = adminService.getAdmin();
        map.put("users", userList);
        return "Admin/showAdmin";
    }

    /**
     * 添加管理员
     * @param user
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAdmin(User user){
        user.setRole("ROLE_ADMIN");
        adminService.addAdmin(user);
        return "添加成功";
    }

    /**
     * 修改管理员信息
     * @param map
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.GET)
    public String uptateAdmin(Map<String,Object> map, int id){
        User user = adminService.findAdmin(id);
        map.put("user",user);
        return "Admin/updateAdmin";
    }

    /**
     * 修改管理员信息
     * @param user
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    public String updateAdmin(User user){
        if(adminService.updateAdmin(user))
            return "修改成功";
        else
            return "修改失败";
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @RequestMapping("/deleteAdmin")
    public String deleteAdmin(int id){
        adminService.deketeAdmin(id);
        return null;
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/inform")
    public int inform(String empId, String message) throws IOException {
        String s[] = empId.split(",");
        int c = 0;
        if (s.length != 0) {
            Account account = adminService.getSMS();
            Information information = new Information(account.getNumber(), account.getNote());
            List<Status> statuss = adminService.getStatus("通知面试");
            Status status;
            if (statuss.size() != 0) {
                status = statuss.get(0);
            } else {
                status = adminService.setStatus("通知面试");
            }
            List<String> phoneList = new ArrayList<String>();
            List<String> idList = new ArrayList<String>();
            for (int i = 1; i <= s.length; i++) {
                phoneList.add(adminService.getNotInform(s[i-1]).getePhone());
                idList.add(s[i-1]);
                if (i % 20 == 0 || i == s.length) {
                    String mes = information.doSend(phoneList, message);
                    int mess = Integer.parseInt(mes);
                    if (mess == 20 || (i == s.length && mess == (i % 20))) {
                        adminService.updateStatus(status.getsId(), idList);
                    }
                    phoneList = new ArrayList<String>();
                    idList = new ArrayList<String>();
                }
            }
        }
        return c;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/informDepart")
    public String informDepart(Map<String,Object> map) {
        map.put("dep",adminService.getDepartmentList());
        return "/Admin/informDepart";
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/informDep")
    public int informDep(int depId, String message) throws IOException {
        System.out.println(depId + message);
        List<Employee> employees = adminService.getNotInform(depId);
        int c = 0;
        if (employees.size() != 0) {
            Account account = adminService.getSMS();
            Information information = new Information(account.getNumber(), account.getNote());
            List<Status> statuss = adminService.getStatus("通知面试");
            Status status;
            if (statuss.size() != 0) {
                status = statuss.get(0);
            } else {
                status = adminService.setStatus("通知面试");
            }
            List<String> phoneList = new ArrayList<String>();
            List<String> idList = new ArrayList<String>();
            for (int i = 1; i <= employees.size(); i++) {
                phoneList.add(employees.get(i-1).getePhone());
                idList.add(employees.get(i-1).geteId());
                if (i % 20 == 0 || i == employees.size()) {
                    String mes = information.doSend(phoneList, message);
                    int mess = Integer.parseInt(mes);
                    if (mess == 20 || (i == employees.size() && mess == (i % 20))) {
                        adminService.updateStatus(status.getsId(), idList);
                    }
                    phoneList = new ArrayList<String>();
                    idList = new ArrayList<String>();
                }
            }
        }
        return c;
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/updateStatus")
    public String updateStatus(Integer statusId, String empId) throws IOException {
        String s[] = empId.split(",");
        if(statusId < 0 ) {
            return "false";
        } else {
            List<String> stringList = new ArrayList<String>();
            for (String i:s) {
                stringList.add(i);
            }
            return "" + adminService.updateStatus(statusId, stringList);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER','ROLE_ADMIN')")
    @RequestMapping("/findNew")
    public String findNew(Map<String, Object> map,String id, String[] dep, String[] xiBie, String[] sex, String year, String clazz, String pagination, String count){
        int pageNo = 1;
        int pageSize = 10;
        try {
            pageNo = Integer.parseInt(pagination);
            if (pageNo < 1 ) pageNo = 1;
        } catch (Exception e){}
        try {
            pageSize = Integer.parseInt(count);
            if (pageSize < 1 ) pageSize = 10;
        } catch (Exception e){}
        if (dep == null || dep.length <= 0){
            map.put("depid","0");
        } else {
            map.put("depid",dep[0]);
        }
        String status[] = {"1"};
        Pager<Employee> employeePagerImp = adminService.fileEmployee(id, dep, xiBie, sex, year, clazz, status, pageNo, pageSize);
        map.put("dep", adminService.findDepartment());
        map.put("employeePager", employeePagerImp);
        return "Admin/showNew";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER')")
    @RequestMapping("/showEmp")
    public String showEmp(Map<String, Object> map){
        map.put("dep", adminService.getDepartmentList());
        map.put("xibie", adminService.getXiBieList());
        map.put("status", adminService.getStatusList());
        return "Admin/showEmp";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER')")
    @RequestMapping("/outEmployee")
    public String outEmployee(Map<String, Object> map, String id, String[] dep, String[] xiBie, String[] sex, String year, String clazz, String[] status) throws IOException {
        Pager<Employee> employeePager = adminService.fileEmployee(id, dep, xiBie, sex, year, clazz, status, 0, 0);
        String path = System.getProperty("evan.webapp") + "File" + File.separator;
        String name = "employeeList" + new Date().getTime() + ".xls";
        String[] topLine = {"工号","姓名","性别","部门","联系方式","学号","系别","班级","邮箱","状态"};
        String[][] content = new String[employeePager.getResult().size()][10];
        List<Employee> employees = employeePager.getResult();
        for (int i=0;i<employees.size();i++) {
            Employee employee = employees.get(i);
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
            content[i][9] = employee.geteStatus().getsStatus();
        }
        new Excel().createExcel((path + name), "员工表", topLine, content);
        map.put("fileName", name);
        return "/download";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER','ROLE_USER')")
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String name) throws IOException {
        String path = System.getProperty("evan.webapp") + "File" + File.separator;
        File file = new File(path + name);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String(name.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
