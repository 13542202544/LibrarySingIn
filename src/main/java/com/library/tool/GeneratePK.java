package com.library.tool;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.QueryParameters;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by mobk on 2015/11/25.
 */
public class GeneratePK implements Configurable, IdentifierGenerator {
    public String year;// 年份
    public String classname; //实体类的类名
    public String pk;//主键名字
    public String idLength;//ID的长度

    /**
     * 取得自定义的值
     */
    public void configure(Type arg0, Properties arg1, Dialect arg2)
            throws MappingException {
        //格式化日期时间
        SimpleDateFormat time = new SimpleDateFormat("yyyy");
        Date date = new Date();
        this.year = time.format(date);
        this.classname = arg1.getProperty("classname");
        this.pk = arg1.getProperty("pk");
        this.idLength = arg1.getProperty("idLength");
    }

    /**
     * 生成主键
     */
    public Serializable generate(SessionImplementor arg0, Object arg1)
            throws HibernateException {
        //获得主键的长度
        int leng = Integer.valueOf(idLength);
        //需要查询数据库中最大的ID号
        StringBuffer sql = new StringBuffer("select max(a.").append(pk)
                .append(") from ")
                .append(classname)
                .append(" as a where a.")
                .append(pk)
                .append(" like '")
                .append(year)
                .append("%'");
        QueryParameters qp = new QueryParameters();
        List ls = arg0.list(sql.toString(), qp);
        String max = (String) ls.get(0);
        int i = 0;
        //如果是第一次添加记录那么就是类似user000000001
        if (max == null || max.trim().equals("")) {
            max = "1";
            for(; i < leng-1-year.length(); i++) {
                max = "0" + max;
            }
            max = year + max;
            i = 0;
            return max;
        }//不是第一次的操作,并且记录的长度没有超过从配置文件中读取的长度
        else if(max != null && max.length() <= leng) {
            Integer imax = Integer.parseInt(max) + 1;
            String returnnum = String.valueOf(imax);
            int zero = leng-returnnum.length();
            for(; i < zero; i++) {
                returnnum = "0" + returnnum;
            }
            i = 0;
            return returnnum;
        }//不是第一次的操作,记录的长度超过了从配置文件中读取的长度
        else {
            leng = max.length();
            Integer imax = Integer.parseInt(max) + 1;
            String returnnum = String.valueOf(imax);
            int zero = leng-returnnum.length();
            for(; i < zero; i++) {
                returnnum = "0" + returnnum;
            }
            return returnnum;
        }

    }

}