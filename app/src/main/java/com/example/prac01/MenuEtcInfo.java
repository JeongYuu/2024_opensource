package com.example.prac01;

public class MenuEtcInfo {
    String name;
    String description;

    public MenuEtcInfo(){

    }
    public MenuEtcInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
