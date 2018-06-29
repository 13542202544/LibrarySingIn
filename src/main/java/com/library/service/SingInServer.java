package com.library.service;

import com.library.model.Employee;
import com.library.model.SingIn;
import com.library.model.WorkContent;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mobk on 2015/11/28.
 */
@Service
public class SingInServer extends BaseService {

    /**
     * 签到开始时间
     * @param jobId
     * @param workContent
     * @param notes
     * @param beJobId
     * @return
     */
    public boolean startTime(String jobId, int workContent, String notes, String beJobId){
        if (getSingInBaseDao().find("FROM SingIn WHERE siEmployee = '" + jobId + "' AND siEndTime IS NULL").size() > 0) {
            return false;
        }
        SingIn singIn = new SingIn();
        singIn.setSiEmployee(getEmployeeBaseDao().findById(Employee.class, jobId));
        singIn.setSiStartTime(new Date());
        singIn.setSiWorkContent(getWorkContentBaseDao().findById(WorkContent.class, workContent));
        singIn.setSiNotes(notes);
        if (!("").equals(beJobId) && beJobId != null) {
            if (getSingInBaseDao().find("FROM SingIn WHERE siEndTime IS NULL AND siReplaceEmployee = '" + beJobId + "'").size() > 0) {
                return false;
            }
            singIn.setSiReplaceEmployee(getEmployeeBaseDao().findById(Employee.class, beJobId));
        }
        return getSingInBaseDao().save(singIn);
    }

    /**
     * 签到结束时间
     * @param SIid
     * @param workContent
     * @param notes
     * @return
     */
    public boolean endTime(String SIid, int workContent, String notes){
        SingIn singIn = getSingInBaseDao().findById(SingIn.class, SIid);
        if (singIn.getSiWorkContent().getWcId() != workContent) {
            return false;
        }
        singIn.setSiEndTime(new Date());
        singIn.setSiNotes(singIn.getSiNotes() + " |-----| " + notes);
        return getSingInBaseDao().saveOrUpdate(singIn);
    }

    /**
     * 获取总的工作时间
     * @param SIid
     */
    public void getWorkTime(String SIid){
        SimpleDateFormat time = new SimpleDateFormat("E");
        SingIn singIn = getSingInBaseDao().findById(SingIn.class, SIid);
        Date startTime = singIn.getSiStartTime();
        System.out.println(time.format(startTime));
        Date endTime = singIn.getSiEndTime();
        System.out.println(endTime);
    }

    /**
     * 登录
     * @param jobId
     * @param number
     * @return
     */
    public boolean login(String jobId, String number) {
        List<Employee> employeeList = getEmployeeBaseDao().listByHql("FROM Employee as e WHERE e.eId = '" + jobId + "' AND e.eNumber = '" + number + "'");
        if (employeeList.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

}
