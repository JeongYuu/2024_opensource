package com.example.prac01;

public class MenuInfo {
    private String name;
    private String bread;
    private String cheese;
    private String sauce;
    private String vege;
    private String add;

    public MenuInfo() {

    }
    public MenuInfo(String name, String bread, String cheese, String sauce, String vege, String add) {
        this.name = name;
        this.bread = bread;
        this.cheese = cheese;
        this.sauce = sauce;
        this.vege = vege;
        this.add = add;
    }

    public String getName() {
        return name;
    }

    public String getBread() {
        return bread;
    }

    public String getCheese() {
        return cheese;
    }

    public String getSauce() {
        return sauce;
    }

    public String getVege() {
        return vege;
    }

    public String getAdd() {
        return add;
    }
}
