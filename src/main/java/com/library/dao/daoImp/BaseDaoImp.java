package com.library.dao.daoImp;

import com.library.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mobk on 2015/11/25.
 */
@Repository
public class BaseDaoImp<T> extends DaoImp implements BaseDao<T> {

    public boolean delete(T t) {
        try {
            getSession().delete(t);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean update(T t) {
        try {
            getSession().update(t);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean update(String hql){
        try{
            int ret = getSession().createQuery(hql).executeUpdate();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveOrUpdate(T t) {
        try {
            getSession().saveOrUpdate(t);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean save(T t) {
        try {
            getSession().save(t);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public List<T> find(String hql) {
        try {
            getSession().cancelQuery();
            return getSession().createQuery(hql).list();
        } catch (Exception e) {
        }
        return null;
    }

    public T findById(Class<T> t, Serializable id) {
        try {
            return (T) getSession().get(t, id);
        } catch (Exception e) {
            return null;
        }
    }

    public T byHql(String hql) {
        try {
            return (T) getSession().createQuery(hql).list().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> listByHql(String hql) {
        try {
            return getSession().createQuery(hql).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> byNativeSQL(String sql) {
        return getSession().createSQLQuery(sql).list();
    }

    public List<T> getAll(Class<T> t) {
        return getSession().createQuery("FROM " + t.getSimpleName()).list();
    }

    public Pager<T> paging(Pager<T> pager){
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from " + pager.getT().getSimpleName() + " where 1=1");
        Iterator it = pager.getMap().keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            try {
                String s1[] = (String[]) pager.getMap().get(key);
                hql.append(" and (1=2");
                for (Object i : s1) {
                    hql.append(" or " + key.toString() + " = '" + i + "'");
                }
                hql.append(")");
            } catch (Exception e) {
                hql.append(" and " + key.toString() + " = '" + pager.getMap().get(key) + "'");
            }
        }

        Iterator it2 = pager.getLikeMap().keySet().iterator();
        while (it2.hasNext()) {
            Object key = it2.next();
            try {
                String s1[] = (String[]) pager.getLikeMap().get(key);
                hql.append(" and (1=2");
                for (Object i : s1) {
                    hql.append(" or " + key.toString() + " = '" + i + "'");
                }
                hql.append(")");
            } catch (Exception e) {
                hql.append(" and " + key.toString() + " like " + pager.getLikeMap().get(key));
            }
        }


        try {
            Query query = getSession().createQuery(hql.toString());
            pager.setRowCount(Integer.parseInt(query.list().get(0).toString()));
            if (pager.getPageNo() > 0 && pager.getPageSize() > 0) {
                pager.setPageNumber();
                if (pager.getPageNo() > pager.getPageNumber()) {
                    pager.setPageNo(pager.getPageNumber());
                }
            }
        } catch (RuntimeException re) {
            throw re;
        }


        String queryHql = hql.substring(15);
        try {
            Query query = getSession().createQuery(queryHql.toString());
            if (pager.getPageNo() > 0 && pager.getPageSize() > 0) {
                query.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
                query.setMaxResults(pager.getPageSize());
            }
            pager.setResult(query.list());
        } catch (RuntimeException re) {
            throw re;
        }

        return pager;
    }

    public Query getQuery(String hql) {
        return getSession().createQuery(hql);
    }

}
