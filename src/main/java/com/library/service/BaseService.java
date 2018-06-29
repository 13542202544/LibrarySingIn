package com.library.service;

import com.library.dao.BaseDao;
import com.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mobk on 2015/11/25.
 */
public class BaseService {

    @Autowired
    private BaseDao<Department> departmentBaseDao;

    @Autowired
    private BaseDao<Employee> employeeBaseDao;

    @Autowired
    private BaseDao<SingIn> singInBaseDao;

    @Autowired
    private BaseDao<Work> workBaseDao;

    @Autowired
    private BaseDao<WorkContent> workContentBaseDao;

    @Autowired
    private BaseDao<WorkTime> workTimeBaseDao;

    @Autowired
    private BaseDao<XiBie> xiBieBaseDao;

    @Autowired
    private BaseDao<DutyTime> dutyTimeBaseDao;

    @Autowired
    private BaseDao<User> userBaseDao;

    @Autowired
    private BaseDao<UserExtends> userExtendsBaseDao;

    @Autowired
    private BaseDao<Vacation> vacationBaseDao;

    @Autowired
    private BaseDao<Test> testBaseDao;

    @Autowired
    private BaseDao<Status> statusBaseDao;

    @Autowired
    private BaseDao<Account> accountBaseDao;

    @Autowired
    private BaseDao<Graded> gradedBaseDao;

    @Autowired
    private BaseDao<GradedItem> gradedItemBaseDao;

    @Autowired
    private BaseDao<InterviewSI> interviewSIBaseDao;

    public BaseDao<Graded> getGradedBaseDao() {
        return gradedBaseDao;
    }

    public BaseDao<GradedItem> getGradedItemBaseDao() {
        return gradedItemBaseDao;
    }

    public BaseDao<InterviewSI> getInterviewSIBaseDao() {
        return interviewSIBaseDao;
    }

    public BaseDao<Account> getAccountBaseDao() {
        return accountBaseDao;
    }

    public BaseDao<Status> getStatusBaseDao() {
        return statusBaseDao;
    }

    public BaseDao<Test> getTestBaseDao() {
        return testBaseDao;
    }

    public BaseDao<Vacation> getVacationBaseDao() {
        return vacationBaseDao;
    }

    public BaseDao<Department> getDepartmentBaseDao() {
        return departmentBaseDao;
    }

    public BaseDao<Employee> getEmployeeBaseDao() {
        return employeeBaseDao;
    }

    public BaseDao<SingIn> getSingInBaseDao() {
        return singInBaseDao;
    }

    public BaseDao<Work> getWorkBaseDao() {
        return workBaseDao;
    }

    public BaseDao<WorkContent> getWorkContentBaseDao() {
        return workContentBaseDao;
    }

    public BaseDao<WorkTime> getWorkTimeBaseDao() {
        return workTimeBaseDao;
    }

    public BaseDao<XiBie> getXiBieBaseDao() {
        return xiBieBaseDao;
    }

    public BaseDao<DutyTime> getDutyTimeBaseDao() {
        return dutyTimeBaseDao;
    }

    public BaseDao<User> getUserBaseDao() {
        return userBaseDao;
    }

    public BaseDao<UserExtends> getUserExtendsBaseDao() {
        return userExtendsBaseDao;
    }

    public List<Test> getTestList() {
        return testBaseDao.getAll(Test.class);
    }

    public List<Vacation> getVacationList() {
        return vacationBaseDao.getAll(Vacation.class);
    }

    public List<Department> getDepartmentList() {
        return departmentBaseDao.getAll(Department.class);
    }

    public List<Employee> getEmployeeList() {
        return employeeBaseDao.getAll(Employee.class);
    }

    public List<SingIn> getSingInList() {
        return singInBaseDao.getAll(SingIn.class);
    }

    public List<Work> getWorkList() {
        return workBaseDao.getAll(Work.class);
    }

    public List<WorkContent> getWorkContentList() {
        return workContentBaseDao.getAll(WorkContent.class);
    }

    public List<WorkTime> getWorkTimeList() {
        return workTimeBaseDao.getAll(WorkTime.class);
    }

    public List<XiBie> getXiBieList() {
        return xiBieBaseDao.getAll(XiBie.class);
    }

    public List<DutyTime> getDutyTimeList() {
        return dutyTimeBaseDao.getAll(DutyTime.class);
    }

    public List<User> getUserList() {
        return userBaseDao.getAll(User.class);
    }

    public List<UserExtends> getUserExtendsList() {
        return userExtendsBaseDao.getAll(UserExtends.class);
    }

    public List<Status> getStatusList() {
        return statusBaseDao.getAll(Status.class);
    }

    public List<InterviewSI> getInterviewSIList() {
        return interviewSIBaseDao.getAll(InterviewSI.class);
    }

    public Employee getEmployeeById(String id){
        return employeeBaseDao.findById(Employee.class,id);
    }

    @Autowired
    private BaseDao<Test2> test2BaseDao;

    public BaseDao<Test2> getTest2BaseDao() {
        return test2BaseDao;
    }
}
