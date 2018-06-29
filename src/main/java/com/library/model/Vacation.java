package com.library.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LinTi on 2016/7/16.
 */
@Entity
@Table(name="vacation")
public class Vacation {

    private int vId;
    private String vCause;
    private Date vDate;
    private char vForm;  //订单状态 c为审核中(check) v为审核完成(valid) n为审核不通过(nullity)

    private Employee vEmployee;
    private Work vWork;

    @Id
    @GeneratedValue
    public int getvId() {
        return vId;
    }

    public void setvId(int vId) {
        this.vId = vId;
    }

    public String getvCause() {
        return vCause;
    }

    public void setvCause(String vCause) {
        this.vCause = vCause;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public char getvForm() {
        return vForm;
    }

    public void setvForm(char vForm) {
        this.vForm = vForm;
    }

    @ManyToOne
    @JoinColumn
    public Employee getvEmployee() {
        return vEmployee;
    }

    public void setvEmployee(Employee vEmployee) {
        this.vEmployee = vEmployee;
    }

    @ManyToOne
    @JoinColumn
    public Work getvWork() {
        return vWork;
    }

    public void setvWork(Work vWork) {
        this.vWork = vWork;
    }
}
