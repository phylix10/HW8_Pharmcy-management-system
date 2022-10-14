package com.alireza.model;

import com.alireza.model.enumeration.Roles;

public class Admin extends BaseUser{

    public Admin() {
    }

    public Admin(String name, String username, String password, int age, String nationalCode, String role) {
        super(name, username, password, age, nationalCode, Roles.valueOf(role));
    }

    public Admin(int id, String name, String username, String password, int age, String nationalCode, String role) {
        super(id, name, username, password, age, nationalCode, Roles.valueOf(role));
    }

    public Admin(int id) {
        super(id);
    }
}
