package com.vaibhav.project.entities.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserDetails {
    @Id
    @Column(length = 10,nullable = false)
    private String loginID;

    @Column(length = 100,nullable = false)
    private String userName;

    @Column(length = 100,nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer active;

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
