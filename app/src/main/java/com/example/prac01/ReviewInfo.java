package com.example.prac01;

public class ReviewInfo {
    String user_id;
    String menu_name;
    String content;
    public ReviewInfo() {

    }

    public ReviewInfo(String user_id, String menu_name, String content) {
        this.user_id = user_id;
        this.menu_name = menu_name;
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
