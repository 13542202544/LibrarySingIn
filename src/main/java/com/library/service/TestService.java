package com.library.service;

import com.library.model.Employee;
import com.library.model.Test;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 * Created by LinTi on 2016/9/24.
 */
@Service
public class TestService extends BaseService {

    public Session testSql(){
        Employee employee = new Employee();
        employee.seteName("");
        employee.seteEmail("123");
        employee.setePhone("123");
        getEmployeeBaseDao().save(employee);
        return getTestBaseDao().getSession();
    }

}
