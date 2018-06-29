package com.library.service;

import com.library.model.Employee;
import com.library.model.Graded;
import com.library.model.GradedItem;
import com.library.model.InterviewSI;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by LinTi on 2016/9/29.
 */
@Service
public class InterviewService extends BaseService {



    /**
     * 添加评分项
     */
    public boolean setGradItem(List<GradedItem> gradedItems) {
        for (GradedItem g: gradedItems) {
            if (!getGradedItemBaseDao().saveOrUpdate(g)) return false;
        }
        return true;
    }

    /**
     * 删除评分项
     */
    public boolean delGradItem(int[] id) {
        for (int i:id) {
            if (!getGradedItemBaseDao().delete(getGradedItemBaseDao().findById(GradedItem.class,i))) return false;
        }
        return true;
    }

    /**
     * 面试签到
     */
    public String si(String empId, String email) {
        Employee employee = getEmployeeById(empId);
        employee.seteEmail(email);
//        employee.seteStatus(getStatusBaseDao().byHql("FROM Status WHERE sStatus = '通知面试'"));
        getEmployeeBaseDao().saveOrUpdate(employee);
        char t = (char) (employee.geteDepartment().getdId() + 96);
        SimpleDateFormat time = new SimpleDateFormat("yy");
        Date date = new Date();
        String year = time.format(date);
        int maxNum;
        Object number = getGradedItemBaseDao().getQuery("SELECT MAX(number) FROM InterviewSI WHERE number LIKE '" + t + year + "%'").list().get(0);
        if (number==null) {
            maxNum = Integer.parseInt(year + "0001");
        } else {
            String nu = number.toString();
            nu = nu.substring(1,nu.length());
            maxNum = Integer.parseInt(nu) + 1;
        }
        String XN = t + "" + maxNum;
        InterviewSI interviewSI = new InterviewSI();
        interviewSI.setEmployee(employee);
        interviewSI.setNumber(XN);
        getInterviewSIBaseDao().save(interviewSI);
        return XN;
    }

    /**
     * 面试评分
     */
    public boolean graded(List<Graded> gradeds) {
        for (Graded g:gradeds) {
            g.setGradedItemName(getGradedItemBaseDao().findById(GradedItem.class,Integer.parseInt(g.getGradedItemName())).getItemName());
            if (!getGradedBaseDao().saveOrUpdate(g)) return false;
        }
        return true;
    }

    public List<GradedItem> getGradedItem() {
        return getGradedItemBaseDao().getAll(GradedItem.class);
    }

    public InterviewSI getInterviewSI(String number) {
        return getInterviewSIBaseDao().byHql("FROM InterviewSI WHERE number = '" + number + "'");
    }

    public List<Employee> getEmpByPhone(String phone) {
        String hql = "FROM Employee WHERE ePhone = '" + phone + "' AND eStatus = '" +
                getStatusBaseDao().getQuery("SELECT sId FROM Status WHERE sStatus = '报名'").list().get(0).toString() + "'";
        List<Employee> employees = getEmployeeBaseDao().find(hql);
        for (Employee e: employees) {
            InterviewSI interviewSI = getInterviewSIBaseDao().byHql("FROM InterviewSI WHERE employee = '" + e.geteId() + "'");
            if (interviewSI != null ){
                interviewSI.setEmployee(null);
            }
            e.setInterviewSI(interviewSI);
        }
        return employees;
    }

    public List<Graded> getGradedByEmp(String eId) {
        List<Graded> gradeds = getGradedBaseDao().listByHql("FROM Graded WHERE employee = '" + eId +"'");
        return gradeds;
    }

    public InterviewSI getSI(String eId) {
        return getInterviewSIBaseDao().byHql("FROM InterviewSI WHERE employee = '" + eId + "'");
    }
}
