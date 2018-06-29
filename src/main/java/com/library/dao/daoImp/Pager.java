package com.library.dao.daoImp;


import com.google.gson.annotations.Expose;
import org.hibernate.Query;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by LinTi on 2016/9/17.
 */
public class Pager<T>{

    @Expose
    private int pageSize;
    @Expose
    private int pageNo;
    @Expose
    private int rowCount;
    @Expose
    private int pageNumber;
    @Expose
    private List<T> result;
//    @Expose
    private Class<T> t;
    @Expose
    private Map map = null;
    @Expose
    private Map likeMap = null;

    public Pager(int pageNo, int pageSize, Class<T> t) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.t = t;
    }

    public Pager(int pageNo, int pageSize, Class<T> t, Map map, Map likeMap) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.t = t;
        this.map = map;
        this.likeMap = likeMap;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber() {
        this.pageNumber = (this.rowCount/this.pageSize) + 1;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Class<T> getT() {
        return t;
    }

    public void setT(Class<T> t) {
        this.t = t;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getLikeMap() {
        return likeMap;
    }

    public void setLikeMap(Map likeMap) {
        this.likeMap = likeMap;
    }
}
