package com.library.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.model.*;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/1/27.
 */
@Service
public class SystemService extends BaseService {

    public String findWork() {

        return null;
    }

    /**
     * 查找当前需要值班工作人员
     *
     * @param date
     * @return
     */
    public String findDutyEmp(Date date) {
        Time time = Time.valueOf(new SimpleDateFormat("HH:mm:ss").format(date).toString());
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(date).toString());
        String week = new SimpleDateFormat("E").format(date);
        String d = null;
        if ((("星期六").equals(week) || ("星期天").equals(week)) && hour < 17) {
            d = new SimpleDateFormat("M月d日").format(date).toString();
        }
        List<DutyTime> dutyTimeList = getDutyTimeBaseDao().listByHql("FROM DutyTime as dt WHERE dt.dtStartTime < '" + time + "' AND dt.dtEndTime > '" + time + "'");
        List<WorkTime> workTimeList = new ArrayList<WorkTime>();
        for (int i = 0; i < dutyTimeList.size(); i++) {
            workTimeList.addAll(getWorkTimeBaseDao().listByHql("FROM WorkTime WHERE wtDutyTime_dtId = '" + dutyTimeList.get(i).getDtId() + "' AND wtTime LIKE '" + week + "%'"));
        }
        List<Work> workList = new ArrayList<Work>();
        for (int i = 0; i < workTimeList.size(); i++) {
            String sql;
            if (d != null) {
                sql = "SELECT * FROM `work` as w WHERE w.notes = '" + d + "' AND w.wWorkTime_wtId = '" + workTimeList.get(i).getWtId() + "' AND w.wEmployee_eId NOT IN (SELECT si.siEmployee_eId FROM singin as si WHERE si.siEndTime IS NULL) AND w.wEmployee_eId NOT IN ( SELECT si.siReplaceEmployee_eId FROM singin as si WHERE si.siEndTime IS NULL AND si.siReplaceEmployee_eId IS NOT NULL )";
            } else {
                sql = "SELECT * FROM `work` as w WHERE w.wWorkTime_wtId = '" + workTimeList.get(i).getWtId() + "' AND w.wEmployee_eId NOT IN (SELECT si.siEmployee_eId FROM singin as si WHERE si.siEndTime IS NULL) AND w.wEmployee_eId NOT IN ( SELECT si.siReplaceEmployee_eId FROM singin as si WHERE si.siEndTime IS NULL AND si.siReplaceEmployee_eId IS NOT NULL )";
            }
            List<Object[]> objectList = getWorkBaseDao().byNativeSQL(sql);
            Work work;
            for (Object[] obj : objectList) {
                work = new Work();
                work.setwId((String) obj[0]);
                work.setNotes((String) obj[1]);
                work.setwEmployee(getEmployeeBaseDao().findById(Employee.class, (String) obj[2]));
                work.setwWorkContent(getWorkContentBaseDao().findById(WorkContent.class, (Integer) obj[3]));
                work.setwWorkTime(getWorkTimeBaseDao().findById(WorkTime.class, (Integer) obj[4]));
                workList.add(work);
            }
        }
        return new Gson().toJson(workList);
    }

    /**
     * 查找当前正在子值班的工作人员
     *
     * @param date
     * @return
     */
    public String findEndDutyEmp(Date date) {
        return new GsonBuilder()
                .setDateFormat("HH:mm:ss")
                .create()
                .toJson(getSingInBaseDao()
                        .listByHql("FROM SingIn as si WHERE si.siEndTime is null")
                );
    }

    /**
     * 增加值班时间(开发用)
     *
     * @param dutyTime
     */
    public void addDutyTime(DutyTime[] dutyTime) {
        for (int i = 0; i < dutyTime.length; i++) {
            getDutyTimeBaseDao().save(dutyTime[i]);
        }
    }

    /**
     * 测试用
     */
    public void sqlTest() {
        Employee e = new Employee();
        e.seteName("张三");
        getEmployeeBaseDao().save(e);
    }
}
