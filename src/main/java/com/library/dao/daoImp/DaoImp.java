package com.library.dao.daoImp;

import com.library.dao.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by mobk on 2015/11/25.
 */
@Repository
public class DaoImp implements Dao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
}
}
