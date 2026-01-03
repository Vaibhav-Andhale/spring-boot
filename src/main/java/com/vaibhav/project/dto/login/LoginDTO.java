package com.vaibhav.project.dto.login;

public class LoginDTO {
    private String loginID;
    private String password;

    public LoginDTO(String loginID, String password) {
        this.loginID = loginID;
        this.password = password;
    }


    public String getLoginID() {
        return loginID;
    }

    public String getPassword() {
        return password;
    }
}
