package com.library.model;

import com.google.gson.annotations.Expose;
import com.library.tool.validator.Depar;
import com.library.tool.validator.Name;
import com.library.tool.validator.Sex;
import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created by mobk on 2015/11/25.
 */
@Entity
@Table(name="employee")
public class Employee {

    @Expose
    private String eId;

    @Name
    @Expose
    private String eName;

    @Sex
    @Expose
    private char eSex;

    @NotBlank(message = "请填写班级")
    @Expose
    private String eClazz;

    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "请正确地填写11位手机号码")
    @Expose
    private String ePhone;

    @Pattern(regexp = "^[0-9]{11}$",message = "请正确地填写11位学号")
    @Expose
    private String eNumber;

    @Email
    @Expose
    private String eEmail;


    @Expose
    private Status eStatus;

    @Depar
    @Expose
    private Department eDepartment;

    @com.library.tool.validator.XiBie
    @Expose
    private XiBie eXiBie;

    @Expose
    private InterviewSI interviewSI;

    private List<Graded> gradeds;


    @Id
    @GeneratedValue(generator = "MyGeneratePK")
    @GenericGenerator(name = "MyGeneratePK", strategy = "com.library.tool.GeneratePK",
            parameters = {@Parameter(name = "classname", value = "Employee"),
                    @Parameter(name = "pk", value = "eId"),
                    @Parameter(name = "idLength", value = "8")
            })
    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public char geteSex() {
        return eSex;
    }

    public void seteSex(char eSex) {
        this.eSex = eSex;
    }

    public String geteClazz() {
        return eClazz;
    }

    public void seteClazz(String eClazz) {
        this.eClazz = eClazz;
    }

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public String geteNumber() {
        return eNumber;
    }

    public void seteNumber(String eNumber) {
        this.eNumber = eNumber;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }

    @ManyToOne
    @JoinColumn
    public Status geteStatus() {
        return eStatus;
    }

    public void seteStatus(Status eStatus) {
        this.eStatus = eStatus;
    }

    @ManyToOne
    @JoinColumn
    public Department geteDepartment() {
        return eDepartment;
    }

    public void seteDepartment(Department eDepartment) {
        this.eDepartment = eDepartment;
    }

    @ManyToOne
    @JoinColumn
    public XiBie geteXiBie() {
        return eXiBie;
    }

    public void seteXiBie(XiBie eXiBie) {
        this.eXiBie = eXiBie;
    }

    @OneToOne
    @JoinColumn
    @Transient
    public InterviewSI getInterviewSI() {
        return interviewSI;
    }

    public void setInterviewSI(InterviewSI interviewSI) {
        this.interviewSI = interviewSI;
    }

    @OneToMany
    @JoinColumn
    @Transient
    public List<Graded> getGradeds() {
        return gradeds;
    }

    public void setGradeds(List<Graded> gradeds) {
        this.gradeds = gradeds;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "eId='" + eId + '\'' +
                ", eName='" + eName + '\'' +
                ", eSex=" + eSex +
                ", eClazz='" + eClazz + '\'' +
                ", ePhone='" + ePhone + '\'' +
                ", eNumber='" + eNumber + '\'' +
                ", eEmail=" + eEmail +
                ", eStatus=" + eStatus +
                ", eDepartment=" + eDepartment +
                ", eXiBie=" + eXiBie +
                ", interviewSI=" + interviewSI +
                '}';
    }


}
