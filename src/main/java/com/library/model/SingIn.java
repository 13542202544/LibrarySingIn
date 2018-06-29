package com.library.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mobk on 2015/11/25.
 */
@Entity
@Table(name="singin")
public class SingIn {

    private String siId;
    private Date siStartTime;
    private Date siEndTime;
    private String siNotes;

    private Employee siEmployee;
    private WorkContent siWorkContent;

    private Employee siReplaceEmployee;

    @Id
    @GeneratedValue(generator = "MyGeneratePK")
    @GenericGenerator(name = "MyGeneratePK", strategy = "com.library.tool.GeneratePKMM",
            parameters = {@Parameter(name = "classname", value = "SingIn"),
                    @Parameter(name = "pk", value = "siId"),
                    @Parameter(name = "status", value = ""),
                    @Parameter(name = "idLength", value = "10")
            })
    public String getSiId() {
        return siId;
    }

    public void setSiId(String siId) {
        this.siId = siId;
    }

    public Date getSiStartTime() {
        return siStartTime;
    }

    public void setSiStartTime(Date siStartTime) {
        this.siStartTime = siStartTime;
    }

    public Date getSiEndTime() {
        return siEndTime;
    }

    public void setSiEndTime(Date siEndTime) {
        this.siEndTime = siEndTime;
    }

    public String getSiNotes() {
        return siNotes;
    }

    public void setSiNotes(String siNotes) {
        this.siNotes = siNotes;
    }

    @OneToOne
    @JoinColumn
    public Employee getSiEmployee() {
        return siEmployee;
    }

    public void setSiEmployee(Employee siEmployee) {
        this.siEmployee = siEmployee;
    }

    @OneToOne
    @JoinColumn
    public WorkContent getSiWorkContent() {
        return siWorkContent;
    }

    public void setSiWorkContent(WorkContent siWorkContent) {
        this.siWorkContent = siWorkContent;
    }

    @OneToOne
    @JoinColumn
    public Employee getSiReplaceEmployee() {
        return siReplaceEmployee;
    }

    public void setSiReplaceEmployee(Employee siReplaceEmployee) {
        this.siReplaceEmployee = siReplaceEmployee;
    }


}
