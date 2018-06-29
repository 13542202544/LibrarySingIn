package com.library.model;

import javax.persistence.*;

/**
 * Created by LinTi on 2016/9/29.
 */
@Entity
@Table(name = "geaded")
public class Graded {

    private int id;
    private Employee employee;
    private String gradedItemName;
    private int score;
    private Employee interviewer;
    private String note;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getGradedItemName() {
        return gradedItemName;
    }

    public void setGradedItemName(String gradedItemName) {
        this.gradedItemName = gradedItemName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @ManyToOne
    @JoinColumn
    public Employee getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Employee interviewer) {
        this.interviewer = interviewer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Graded{" +
                "id=" + id +
                ", employee=" + employee +
                ", gradedItemName=" + gradedItemName +
                ", score=" + score +
                ", note=" + note +
                '}';
    }
}
