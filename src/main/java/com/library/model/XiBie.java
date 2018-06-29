package com.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mobk on 2015/11/24.
 */
@Entity
@Table(name="xibie")
public class XiBie {

    private Integer xbId;
    private String xbName;

    @Id
    @GeneratedValue
    public Integer getXbId() {
        return xbId;
    }

    public void setXbId(Integer xbId) {
        this.xbId = xbId;
    }

    public String getXbName() {
        return xbName;
    }

    public void setXbName(String xbName) {
        this.xbName = xbName;
    }

    @Override
    public String toString() {
        return "XiBie{" +
                "xbId=" + xbId +
                ", xbName='" + xbName + '\'' +
                '}';
    }
}
