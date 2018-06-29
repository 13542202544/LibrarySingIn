package com.library.tool;

import com.library.model.DutyTime;
import com.library.model.Employee;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/1/27.
 */
public class AddDutyTime {

    public static void main(String[] args) {
        ArrayList<DutyTime> dutyTimeArrayList = new ArrayList<DutyTime>();
        String time[][] = {
                {"8:40:00", "10:00:00"},
                {"10:20:00", "11:40:00"},
                {"8:40:00", "11:40:00"},
                {"11:30:00", "14:10:00"},
                {"14:10:00", "15:30:00"},
                {"15:50:00", "17:10:00"},
                {"14:00:00", "17:10:00"},
                {"17:00:00", "21:30:00"},
                {"8:40:00", "17:10:00"}
        };
        for (int i=0; i<time.length; i++) {
            DutyTime dutyTime = new DutyTime();
            dutyTime.setDtStartTime(Time.valueOf(time[i][0]));
            dutyTime.setDtEndTime(Time.valueOf(time[i][1]));
            dutyTimeArrayList.add(dutyTime);
        }
        DutyTime[] dutyTimes = dutyTimeArrayList.toArray(new DutyTime[dutyTimeArrayList.size()]);
        new HibernateSpring().addDutyTime(dutyTimes);
//        new HibernateSpring();
//        Date date = new Date();
//        Time time = Time.valueOf(new SimpleDateFormat("HH:mm:ss").format(date).toString());
//        System.out.println(new SimpleDateFormat("E").format(date));
//        new HibernateSpring().findDutyEmp(date);
    }
}
