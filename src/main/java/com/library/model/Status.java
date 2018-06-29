package com.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LinTi on 2016/7/30.
 */
@Entity
@Table(name="status")
public class Status {

    private int sId;
    private String sStatus;

    @Id
    @GeneratedValue
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    @Override
    public String toString() {
        return "Status{" +
                "sId=" + sId +
                ", sStatus='" + sStatus + '\'' +
                '}';
    }
}
