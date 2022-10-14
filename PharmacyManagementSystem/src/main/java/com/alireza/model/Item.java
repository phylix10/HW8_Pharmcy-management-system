package com.alireza.model;

public class Item {
    private int id;
    private String name;
    private boolean isExist;
    private int price;

    public Item() {
    }

    public Item(int id, String name, boolean isExist, int price) {
        this.id = id;
        this.name = name;
        this.isExist = isExist;
        this.price = price;
    }

    public Item(String name, boolean isExist, int price) {
        this.name = name;
        this.isExist = isExist;
        this.price = price;
    }

    public Item(int id) {
        this.id = id;
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

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
