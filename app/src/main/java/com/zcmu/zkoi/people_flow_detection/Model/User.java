package com.zcmu.zkoi.people_flow_detection.Model;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String user_tel;
    private String user_character;
    private String user_sex;
    private String user_realName;
    private String user_school;
    public static String USERNAME ="username";
    public static String PASSWORD ="password";
    public User(String password , String username){
        this.password = password;
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_character() {
        return user_character;
    }

    public void setUser_character(String user_character) {
        this.user_character = user_character;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_realName() {
        return user_realName;
    }

    public void setUser_realName(String user_realName) {
        this.user_realName = user_realName;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }
}
