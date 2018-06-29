package com.library.service;

import com.library.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LinTi on 2016/7/15.
 */
@Service
public class UserService extends BaseService {

    public List<SingIn> wage(String time, String name) {
        return getSingInBaseDao().listByHql("FROM SingIn as s WHERE s.siId LIKE '" + time + "%' AND s.siEmployee = " + name );
    }

    public List<Work> fineWork(String name) {
        return getWorkBaseDao().listByHql("FROM Work as w WHERE w.wEmployee = " + name );
    }

    public Work getWork(String s) {
        return getWorkBaseDao().findById(Work.class,s);
    }

    public Employee getEmployee(String name) {
        return getEmployeeBaseDao().findById(Employee.class, name);
    }

    public void saveVacation(Vacation vacation) {
        getVacationBaseDao().save(vacation);
    }

    public boolean saveEmployee(Employee employee) {
        employee.seteStatus(getStatusBaseDao().byHql("FROM Status as s WHERE s.sStatus = '报名'"));
        return getEmployeeBaseDao().saveOrUpdate(employee);
    }

    public boolean chickApply(String name, String phone) {
        if (getEmployeeBaseDao().find("FROM Employee WHERE eName='" + name + "' AND ePhone='" + phone + "'").size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateEmp(String name, String phone) {

        return false;
    }

    public Employee selectEmp(String name, String phone) {
        List<Employee> employees = getEmployeeBaseDao().find("FROM Employee WHERE eName='" + name + "' AND ePhone='" + phone + "'");
        if (employees.size() > 0) {
            InterviewSI interviewSI = getInterviewSIBaseDao().byHql("FROM InterviewSI WHERE employee = '" + employees.get(0).geteId() + "'");
            if (interviewSI != null) {
                getInterviewSIBaseDao().delete(interviewSI);
            }
            List<Graded> gradeds = getGradedBaseDao().find("FROM Graded WHERE employee = '" + employees.get(0).geteId() + "'");
            if (gradeds != null) {
                for (Graded g:gradeds) {
                    getGradedBaseDao().delete(g);
                }
            }
            return employees.get(0);
        }
        return null;
    }
}
