package com.library.model;

import javax.persistence.*;

/**
 * Created by mobk on 2015/11/25.
 */
@Entity
@Table(name="worktime")
public class WorkTime {

    private Integer wtId;
    private String wtTime;
    private DutyTime wtDutyTime;

    @Id
    @GeneratedValue
    public Integer getWtId() {
        return wtId;
    }

    public void setWtId(Integer wtId) {
        this.wtId = wtId;
    }

    public String getWtTime() {
        return wtTime;
    }

    public void setWtTime(String wtTime) {
        this.wtTime = wtTime;
    }

    @ManyToOne
    @JoinColumn
    public DutyTime getWtDutyTime() {
        return wtDutyTime;
    }

    public void setWtDutyTime(DutyTime wtDutyTime) {
        this.wtDutyTime = wtDutyTime;
    }
}
