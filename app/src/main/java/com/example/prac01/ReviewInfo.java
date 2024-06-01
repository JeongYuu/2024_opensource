package com.example.prac01;

public class ReviewInfo {
    private String user_id;
    private String menu_name;
    private String content;
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
    public String getMenu_name() {
        return menu_name;
    }
    public String getContent() {
        return content;
    }

}
