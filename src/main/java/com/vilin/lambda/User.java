package com.vilin.lambda;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private Float salary;
    private Date birthday;

    public User(){ }

    public User(Integer id, String name, String password, Float salary, Date birthday) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salary = salary;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
