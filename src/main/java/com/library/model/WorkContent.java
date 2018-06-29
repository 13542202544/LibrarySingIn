package com.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mobk on 2015/11/25.
 */
@Entity
@Table(name="workcontent")
public class WorkContent {

    private Integer wcId;
    private String wcCon;

    @Id
    @GeneratedValue
    public Integer getWcId() {
        return wcId;
    }

    public void setWcId(Integer wcId) {
        this.wcId = wcId;
    }

    public String getWcCon() {
        return wcCon;
    }

    public void setWcCon(String wcCon) {
        this.wcCon = wcCon;
    }
}
