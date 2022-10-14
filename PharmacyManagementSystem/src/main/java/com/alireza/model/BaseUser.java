package com.alireza.model;

import com.alireza.model.enumeration.Roles;

public abstract class BaseUser {
    private int id;
    private String name;
    private String username;
    private String password;
    private int age;
    private String nationalCode;
    private Roles role;

    public BaseUser() {
    }

    public BaseUser(String name) {
        this.name = name;
    }

    public BaseUser(String name, String username, String password, int age, String nationalCode) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.nationalCode = nationalCode;
    }

    public BaseUser(String name, String username, String password, int age, String nationalCode, Roles role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.nationalCode = nationalCode;
        this.role = role;
    }

    public BaseUser(int id) {
        this.id = id;
    }

    public BaseUser(int id, String name, String username, String password, int age, String nationalCode, Roles role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.nationalCode = nationalCode;
        this.role = role;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
