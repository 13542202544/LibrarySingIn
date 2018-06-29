package com.library.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * Created by LinTi on 2016/9/29.
 */
@Entity
@Table(name = "interviewsi")
//@JsonIgnoreProperties(value = {"cities"})
public class InterviewSI {

    @Expose
    private int id;

    @Expose
    private String number;

    private Employee employee;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToOne
    @JoinColumn
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "InterviewSI{" +
                "id=" + id +
                ", number=" + number +
                ", employee=" + employee +
                '}';
    }
}
