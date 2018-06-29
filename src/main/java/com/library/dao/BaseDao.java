package com.library.dao;

import com.library.dao.daoImp.Pager;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mobk on 2015/11/25.
 */
public interface BaseDao<T> extends Dao {

    boolean delete(T t);

    boolean update(T t);

    boolean update(String hql);

    boolean saveOrUpdate(T t);

    boolean save(T t);

    List<T> find(String hql);

    T findById(Class<T> t, Serializable id);

    T byHql(String hql);

    List<T> listByHql(String hql);

    List<Object[]> byNativeSQL(String sql);

    List<T> getAll(Class<T> c);

    Pager<T> paging(Pager<T> pager);

    Query getQuery(String hql);
}
