package com.example.xiaoguizi.mytest.dao;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by xiaoguizi on 16/5/30.
 */
@Table(name = "student")
public class Student {
    @Column(name = "name")
    private String name;
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "sex")
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
