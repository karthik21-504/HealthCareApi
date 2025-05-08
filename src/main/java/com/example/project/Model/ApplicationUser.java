package com.example.project.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApplicationUser {
    @Id
    private String user_name;
    private String email;
    private String password;
    private String mobile_number;
    private String address;

    public ApplicationUser() {
    }

    public ApplicationUser(String user_name, String email, String password, String mobile_number, String address) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
    }

    public ApplicationUser(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
