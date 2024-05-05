package com.example.prac01;

public class Menu {
    String name;
    String bread;
    String cheese;
    String sauce;
    String vege;
    String add;

    public Menu(String name, String bread, String cheese, String sauce, String vege, String add) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getVege() {
        return vege;
    }

    public void setVege(String vege) {
        this.vege = vege;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}
