package com.alireza.model;

public class Patient extends BaseUser{

    public Patient() {
    }

    public Patient(String name) {
        super(name);
    }

    public Patient(int id) {
        super(id);
    }

    public Patient(String name, String username, String password, int age, String nationalCode) {
        super(name, username, password, age, nationalCode);
    }
}
