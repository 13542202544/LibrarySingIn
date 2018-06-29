package com.library.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by mobk on 2015/11/25.
 */
@Entity
@Table(name="work")
public class Work {

    private String wId;

    private Employee wEmployee;
    private WorkContent wWorkContent;
    private WorkTime wWorkTime;
    private String notes;

    @Id
    @GeneratedValue(generator = "MyGeneratePK")
    @GenericGenerator(name = "MyGeneratePK", strategy = "com.library.tool.GeneratePKMM",
            parameters = {@Parameter(name = "classname", value = "Work"),
                    @Parameter(name = "pk", value = "wId"),
                    @Parameter(name = "status", value = ""),
                    @Parameter(name = "idLength", value = "10")
            })
    public String getwId() {
        return wId;
    }

    public void setwId(String wId) {
        this.wId = wId;
    }

    @ManyToOne
    @JoinColumn
    public Employee getwEmployee() {
        return wEmployee;
    }

    public void setwEmployee(Employee wEmployee) {
        this.wEmployee = wEmployee;
    }

    @ManyToOne
    @JoinColumn
    public WorkContent getwWorkContent() {
        return wWorkContent;
    }

    public void setwWorkContent(WorkContent wWorkContent) {
        this.wWorkContent = wWorkContent;
    }

    @ManyToOne
    @JoinColumn
    public WorkTime getwWorkTime() {
        return wWorkTime;
    }

    public void setwWorkTime(WorkTime wWorkTime) {
        this.wWorkTime = wWorkTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
