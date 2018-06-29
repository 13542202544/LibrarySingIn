package com.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LinTi on 2016/9/29.
 */
@Entity
@Table(name = "gradeditem")
public class GradedItem {

    private int id;
    private String itemName;
    private int maxScore;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "GradedItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", maxScore=" + maxScore +
                '}';
    }
}
