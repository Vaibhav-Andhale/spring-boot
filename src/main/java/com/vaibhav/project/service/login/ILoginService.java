package com.vaibhav.project.service.login;

import com.vaibhav.project.dto.login.LoginDTO;
import com.vaibhav.project.exception.CustomException;

public interface ILoginService {
     void login(LoginDTO loginDTO) throws CustomException;
}
