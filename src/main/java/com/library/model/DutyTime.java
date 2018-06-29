package com.library.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/27.
 */
@Entity
@Table(name="dutytime")
public class DutyTime {

    private int dtId;
    private Time dtStartTime;
    private Time dtEndTime;
    private Set<WorkTime> dtWorkTimes;

    @Id
    @GeneratedValue
    public int getDtId() {
        return dtId;
    }

    public void setDtId(int dtId) {
        this.dtId = dtId;
    }

    public Time getDtStartTime() {
        return dtStartTime;
    }

    public void setDtStartTime(Time dtStartTime) {
        this.dtStartTime = dtStartTime;
    }

    public Time getDtEndTime() {
        return dtEndTime;
    }

    public void setDtEndTime(Time dtEndTime) {
        this.dtEndTime = dtEndTime;
    }

    @OneToMany
    public Set<WorkTime> getDtWorkTimes() {
        return dtWorkTimes;
    }

    public void setDtWorkTimes(Set<WorkTime> dtWorkTimes) {
        this.dtWorkTimes = dtWorkTimes;
    }
}
