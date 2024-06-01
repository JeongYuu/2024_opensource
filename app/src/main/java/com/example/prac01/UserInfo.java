package com.example.prac01;

public class UserInfo {
    private String email;
    private String password;
    public UserInfo() {}

    public UserInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

}
