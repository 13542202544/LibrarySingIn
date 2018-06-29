package com.library.service;

import com.library.dao.daoImp.Pager;
import com.library.model.*;
import com.library.tool.Excel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mobk on 2015/11/25.
 */
@Service
public class AdminService extends BaseService {

    /**
     * 批量导入员工
     *
     * @param excelPath
     */
    public void importEmployee(String excelPath) {
        String[][] array = new Excel().openExcel(excelPath);
        Employee employee;
        Department department;
        XiBie xiBie;
        User user;
        Status status = getStatusBaseDao().findById(Status.class, 1);
        for (int i = 1; i < 54; i++) {
            System.out.println("iiiiiiiiii=" + i + " ,length=" + array.length);
            employee = new Employee();
            user = new User();
            if (!array[i][4].equals("")) {
                employee.seteName(array[i][0]);
                employee.seteSex((array[i][1].equals("男")) ? ('1') : ((array[i][1].equals("女") ? ('2') : ('3'))));
                employee.seteClazz(array[i][2]);
                employee.setePhone(array[i][3]);
                employee.seteNumber(array[i][6]);
                System.out.println(array[i][0]);
                List<Department> departmentList = getDepartmentBaseDao().listByHql("FROM Department as d WHERE d.dName = '" + array[i][4] + "'");
                if (departmentList != null) {
                    if (departmentList.size() > 0) {
                        department = departmentList.get(0);
                    } else {
                        department = new Department();
                        department.setdName(array[i][4]);
                        getDepartmentBaseDao().save(department);
                    }
                    employee.seteDepartment(department);
                    List<XiBie> xiBieList = getXiBieBaseDao().listByHql("FROM XiBie as xb WHERE xb.xbName = '" + array[i][5] + "'");
                    if (xiBieList.size() > 0) {
                        xiBie = xiBieList.get(0);
                    } else {
                        xiBie = new XiBie();
                        xiBie.setXbName(array[i][5]);
                        getXiBieBaseDao().save(xiBie);
                    }
                    employee.seteXiBie(xiBie);
                    employee.seteStatus(status);
                    getEmployeeBaseDao().save(employee);
//                    user.setName(employee.geteId());
//                    user.setPassword(array[i][6]);
//                    user.setRole("ROLE_USER");
//                    getUserBaseDao().save(user);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                break;
            }
        }
    }

    /**
     * 查找员工
     *
     * @param id
     * @param dep
     * @param xiBie
     * @param sex
     * @param year
     * @param clazz
     * @param status
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pager<Employee> fileEmployee(String id, String[] dep, String[] xiBie, String[] sex, String year, String clazz, String[] status, int pageNo, int pageSize) {
        Map map = new HashMap();
        Map likeMap = new HashMap();
        if (dep != null && dep.length != 0) {
            map.put("eDepartment", dep);
        }
        if (xiBie != null && xiBie.length != 0) {
            map.put("eXiBie", xiBie);
        }
        if (sex != null && sex.length != 0) {
            map.put("eSex", sex);
        }
        if (year != null && !("").equals(year)) {
            likeMap.put("eId", year + "%");
        }
        if (clazz != null && !("").equals(clazz)) {
            map.put("eClazz", clazz);
        }
        if (status != null && status.length != 0) {
            map.put("eStatus", status);
        }
        if (id != null && !("").equals(id)) {
            map.put("eId", id);
        }

        Pager<Employee> pager = new Pager<Employee>(pageNo, pageSize, Employee.class, map, likeMap);
        pager = getEmployeeBaseDao().paging(pager);
        return pager;
    }

    /**
     * 查找值班
     *
     * @param id
     * @param start
     * @param end
     * @return
     */
    public List<SingIn> fileSingIn(String id, Date start, Date end) {
        String sql = "SELECT * FROM singin WHERE siEmployee_dId = '" + id + "'";
        //需多做实验
        if (start != null && end != null) {
            sql = sql + " AND siStartTime > " + start + " ADN siEndTime < " + end;
        }
        List<Object[]> singIns = getSingInBaseDao().byNativeSQL(sql);
        List<SingIn> singInList = new ArrayList<SingIn>();
        SingIn singIn;
        for (Object[] obj : singIns) {
            singIn = new SingIn();
            singIn.setSiId((String) obj[0]);
            singIn.setSiEndTime((Date) obj[1]);
            singIn.setSiNotes((String) obj[2]);
            singIn.setSiStartTime((Date) obj[3]);
            singIn.setSiEmployee(getEmployeeBaseDao().findById(Employee.class, (String) obj[4]));
            singIn.setSiReplaceEmployee(getEmployeeBaseDao().findById(Employee.class, (String) obj[5]));
            singIn.setSiWorkContent(getWorkContentBaseDao().findById(WorkContent.class, (String) obj[6]));
            singInList.add(singIn);
        }
        return singInList;
    }

    /**
     * 导出值班表模板
     *
     * @param path
     */
    public void outputModelWork(String path) {
        List<Employee> employeeList = getEmployeeBaseDao().find("FROM Employee as e ");
        String content[][] = new String[employeeList.size()][2];
        for (int i = 0; i < content.length; i++) {
            content[i][0] = employeeList.get(i).geteId();
            content[i][1] = employeeList.get(i).geteName();
        }
        String topLine[] = {"ID", "姓名", "星期一 第1-2节", "星期一 第3-4节", "星期一 午班", "星期一 第5-6节", "星期一 第7-8节", "星期一 晚班", "星期二 第1-2节", "星期二 第3-4节", "星期二 午班", "星期二 第5-6节", "星期二 第7-8节", "星期二 晚班", "星期三 第1-2节", "星期三 第3-4节", "星期三 午班", "星期三 第5-6节", "星期三 第7-8节", "星期三 晚班", "星期四 第1-2节", "星期四 第3-4节", "星期四 午班", "星期四 第5-6节", "星期四 第7-8节", "星期四 晚班", "星期五 第1-2节", "星期五 第3-4节", "星期五 午班", "星期五 第5-6节", "星期五 第7-8节", "星期五 晚班", "星期六 白天", "星期天 白天", "星期天 晚班"};
        new Excel().createExcel((path + "/值班表模板.xls"), "值班表", topLine, content);
    }

    /**
     * 导出签到时间表
     *
     * @param path
     */
    public void outputSingIn(String path, String name) {
        List<SingIn> singInList = getSingInBaseDao().listByHql("FROM SingIn as si");
        SimpleDateFormat timeMMdd = new SimpleDateFormat("MM.dd");
        SimpleDateFormat timeHHmmss = new SimpleDateFormat("HH:mm:ss");
        String content[][] = new String[singInList.size()][6];
        int j;
        for (int i = 0; i < content.length; i++) {
            j = 0;
            content[i][j++] = singInList.get(i).getSiEmployee().geteName();
            content[i][j++] = singInList.get(i).getSiWorkContent().getWcCon();
            content[i][j++] = String.valueOf(timeMMdd.format(singInList.get(i).getSiStartTime()));
            content[i][j++] = String.valueOf(timeHHmmss.format(singInList.get(i).getSiStartTime()));
            if (singInList.get(i).getSiEndTime() != null) {
                content[i][j++] = String.valueOf(timeHHmmss.format(singInList.get(i).getSiEndTime()));
                content[i][j++] = String.valueOf((singInList.get(i).getSiEndTime().getTime() - singInList.get(i).getSiStartTime().getTime()) / 1000 / 60);
            } else {
                j++;
                j++;
            }
        }
        String topLine[] = {"姓名", "工作内容", "日期", "签到时间", "结束时间", "时间统计(分钟)"};
        new Excel().createExcel((path + name), "签到表", topLine, content);
    }

    /**
     * 导入值班表
     *
     * @param path
     */
    public void importWork(String path) {
        String content[][] = new Excel().openExcel(path);
        for (int i = 2; i < content.length; i++) {
            for (int j = 2; j < content[i].length; j++) {
                if (!content[i][j].equals("")) {
                    Work work = new Work();
                    work.setwEmployee(getEmployeeBaseDao().findById(Employee.class, content[i][0]));
                    String wt = null;
                    String week = null;
                    System.out.println("---------------------");
                    for (int q = j; q > (j - 6); q--) {
                        System.out.println(content[0][q]);
                        if (!content[0][q].equals("")) {
                            week = content[0][q];
                            wt = content[0][q] + " " + content[1][j];
                            break;
                        }
                    }
                    WorkTime workTime = getWorkTimeBaseDao().byHql("FROM WorkTime as wt WHERE wt.wtTime = '" + wt + "'");
                    if (workTime == null) {
                        workTime = new WorkTime();
                        workTime.setWtTime(wt);
                        String s[] = {"第1-2节", "第3-4节", "上午", "午班", "第5-6节", "第7-8节", "下午", "晚班", "白天"};
                        for (int k = 0; k < s.length; k++) {
                            if (s[k].equals(content[1][j])) {
                                workTime.setWtDutyTime(getDutyTimeBaseDao().findById(DutyTime.class, (k + 1)));
                            }
                        }
                        getWorkTimeBaseDao().save(workTime);
                    }
                    work.setwWorkTime(workTime);
                    WorkContent workContent;
                    if ((("星期六").equals(week) || ("星期天").equals(week)) && !("晚班").equals(content[1][j])) {
                        String[] cons = content[i][j].split(" ");
                        workContent = getWorkContentBaseDao().byHql("FROM WorkContent as wc WHERE wc.wcCon = '" + cons[1] + "'");
                        if (workContent == null) {
                            workContent = new WorkContent();
                            workContent.setWcCon(cons[1]);
                            getWorkContentBaseDao().save(workContent);
                        }
                        work.setNotes(cons[0]);
                    } else {
                        workContent = getWorkContentBaseDao().byHql("FROM WorkContent as wc WHERE wc.wcCon = '" + content[i][j] + "'");
                        if (workContent == null) {
                            workContent = new WorkContent();
                            workContent.setWcCon(content[i][j]);
                            getWorkContentBaseDao().save(workContent);
                        }
                    }
                    work.setwWorkContent(workContent);
                    getWorkBaseDao().save(work);
                }
            }
        }
    }

    /**
     * 添加更改员工信息
     *
     * @param employee
     * @return
     */
    public boolean saveOrUpdateEmp(Employee employee) {
        return getEmployeeBaseDao().saveOrUpdate(employee);
    }

    /**
     * 查找部门
     */

    public List<Department> findDepartment() {
        List<Department> departmentList = getDepartmentBaseDao().listByHql("FROM Department");
        return departmentList;
    }

    /**
     * 查找部门
     */
    public List<XiBie> findXiBie() {
        List<XiBie> XiBieList = getXiBieBaseDao().listByHql("FROM XiBie");
        return XiBieList;
    }

    /**
     * 查找管理员
     *
     * @param id
     * @return
     */
    public User findAdmin(int id) {
        return getUserBaseDao().findById(User.class, id);
    }

    /**
     * 添加管理员
     *
     * @param user
     */
    public void addAdmin(User user) {
        getUserBaseDao().save(user);
    }


    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    public boolean deketeAdmin(int id) {
        return getUserBaseDao().delete(getUserBaseDao().findById(User.class, id));
    }

    /**
     * 更改管理员信息
     *
     * @param user
     * @return
     */
    public boolean updateAdmin(User user) {
        return getUserBaseDao().update(user);
    }

    /**
     * 测试
     */
    @Secured("ROLE_SUPER")
    public void testSeucrity() {
        System.out.println("super");
    }

    /**
     * 获取管理员
     *
     * @return
     */
    public List<User> getAdmin() {
        List<User> admins = getUserBaseDao().find("FROM User WHERE Role = 'ROLE_ADMIN'");
        return admins;
    }

    /**
     * 更改工作人员状态
     *
     * @param statusId
     * @param split
     * @return
     */
    public boolean updateStatus(Integer statusId, List<String> split) {
        String eId = new String();
        for (String s : split) {
            eId = eId + s + ",";
        }
        eId = eId.substring(0, eId.length() - 1);
        getEmployeeBaseDao().update("UPDATE Employee SET eStatus = '" + statusId + "' WHERE eId IN (" + eId + ")");
        return true;
    }

    /**
     * 查找涉及用到的账号信息
     *
     * @param split
     * @param message
     * @return
     * @throws IOException
     */
/*    public int inform(String[] split, String message) throws IOException {
        Account account = getAccountBaseDao().find("FROM Account WHERE category = 'SMS'").get(0);
        Information information = new Information(account.getNumber(), account.getNote());
        int statusId = getStatusBaseDao().find("FROM Status WHERE sStatus ='面试通知'").get(0).getsId();
        int c = 0;
        List<String> stringList = new ArrayList<String>();
        for (int i = 1; i <= split.length; i++) {
            stringList.add(split[i]);
            if (i % 20 == 0 || i == split.length) {
                String mes = information.doSend(stringList, message);
                for (int k = 0; k < stringList.size(); k++) {
                    getEmployeeBaseDao().update("UPDATE Employee SET eStatus=" + statusId + " WHERE eId = '" + stringList.get(k) + "'");
                    c++;
                }
                stringList = new ArrayList<String>();
            }

        }
        return c;
    }*/

    /**
     * 查找涉及用到的账号信息
     */
    public Account getSMS() {
        Account account = getAccountBaseDao().find("FROM Account WHERE category = 'SMS'").get(0);
        return account;
    }

    /**
     * 状态
     *
     * @param name
     * @return
     */
    public List<Status> getStatus(String name) {
        return getStatusBaseDao().find("FROM Status WHERE sStatus ='" + name + "'");
    }

    public Status setStatus(String name) {
        Status s = new Status();
        s.setsStatus(name);
        getStatusBaseDao().save(s);
        return s;
    }


    public List<Employee> getEmployeeByDepId(String depId) {
        return getEmployeeBaseDao().listByHql("FROM Employee WHERE eDeparment = " + depId);
    }

    public Employee getNotInform(String empID) {
        return getEmployeeBaseDao().byHql("FROM Employee WHERE eId = '" + empID + "' AND eStatus = " + getStatus("报名").get(0).getsId());
    }

    public List<Employee> getNotInform(int depId) {
        return getEmployeeBaseDao().listByHql("FROM Employee WHERE eDepartment = '" + depId + "' AND eStatus = " + getStatus("报名").get(0).getsId());
    }
}