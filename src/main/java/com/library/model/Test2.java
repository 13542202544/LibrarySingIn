package com.library.model;

import javax.persistence.*;

/**
 * Created by LinTi on 2016/7/23.
 */
@Entity
@Table(name="test2")
public class Test2 {

    private int id;
    private String name;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
