package com.library.service;

import com.library.model.Employee;
import com.library.model.SingIn;
import com.library.model.WorkContent;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mobk on 2015/11/28.
 */
@Service
public class UiUserService extends BaseService {

    /**
     * 签到开始时间
     * @param jobID
     * @param stuID
     * @param workContent
     * @param notes
     * @return String
     */
    public String startTime(String jobID, String stuID, String workContent, String notes) {
        if(getEmployeeBaseDao().byHql("FROM Employee as e WHERE e.eId = '" + jobID + "' AND e.eNumber = '" + stuID + "'") == null){
            return "工号或学号错误,请检查!";
        }
        String hql = "FROM SingIn as si WHERE si.siId = some(SELECT max(siId) FROM SingIn as s WHERE s.siEmployee = some(from Employee as e WHERE e.eId = '" + jobID + "'))";
        SingIn sing = null;
        try {
            sing = getSingInBaseDao().byHql(hql);
        } catch (Exception e) {
//            sing = new SingIn();
        }
        if (sing == null || (sing.getSiStartTime() != null && sing.getSiEndTime() != null)) {
            SingIn singIn = new SingIn();
            singIn.setSiEmployee(getEmployeeBaseDao().findById(Employee.class, jobID));
            singIn.setSiWorkContent((WorkContent) getWorkContentBaseDao().byHql("FROM WorkContent as wc WHERE wc.wcCon = '" + workContent + "'"));
            singIn.setSiStartTime(new Date());
            singIn.setSiNotes(notes);
            if (getSingInBaseDao().save(singIn)) {
                return "签到成功!";
            } else {
                return "签到失败!请重试!!";
            }
        } else {
            return "你正在上班中!";
        }
    }

    /**
     * 签到结束时间
     * @param jobID
     * @param stuID
     * @param notes
     * @return String
     */
    public String endTime(String jobID, String stuID, String notes) {
        if(getEmployeeBaseDao().byHql("FROM Employee as e WHERE e.eId = '" + jobID + "' AND e.eNumber = '" + stuID + "'") == null){
            return "工号或学号错误,请检查!";
        }
        String hql = "FROM SingIn as si WHERE si.siId = some(SELECT max(siId) FROM SingIn as s WHERE s.siEmployee = some(from Employee as e WHERE e.eId = '" + jobID + "'))";
        SingIn singIn = getSingInBaseDao().byHql(hql);
        if (singIn != null && singIn.getSiEndTime() == null) {
            singIn.setSiEndTime(new Date());
            singIn.setSiNotes(singIn.getSiNotes() + " |-----| " + notes);
            if (getSingInBaseDao().saveOrUpdate(singIn)) {
                return "谢谢!本次上班时间" + ((singIn.getSiEndTime().getTime() - singIn.getSiStartTime().getTime()) / 1000 / 60) + "分钟。";
            } else {
                return "系统错误,请重试!";
            }
        } else {
            return "没有找到你的上班时间!!";
        }
    }

    /**
     * 获取签到时长
     * @param SIid
     */
    public void getWorkTime(String SIid) {
        SimpleDateFormat time = new SimpleDateFormat("E");
        SingIn singIn = getSingInBaseDao().findById(SingIn.class, SIid);
        Date startTime = singIn.getSiStartTime();
        System.out.println(time.format(startTime));
        Date endTime = singIn.getSiEndTime();
        System.out.println(endTime);
    }
}
