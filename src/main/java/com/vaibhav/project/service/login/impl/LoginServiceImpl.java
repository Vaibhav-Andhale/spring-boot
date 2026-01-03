package com.vaibhav.project.service.login.impl;

import com.vaibhav.project.dto.login.LoginDTO;
import com.vaibhav.project.dto.user.UserDetailsDTO;
import com.vaibhav.project.exception.CustomException;
import com.vaibhav.project.service.login.ILoginService;
import com.vaibhav.project.service.user.IUserDetailsService;
import com.vaibhav.project.utils.Message;
import com.vaibhav.project.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    private static final Logger logger= LoggerFactory.getLogger(LoginServiceImpl.class);
    private final IUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final Message message;

    @Autowired
    public LoginServiceImpl(IUserDetailsService userDetailsService,PasswordEncoder passwordEncoder,Message message) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder=passwordEncoder;
        this.message=message;
    }

    public void login(LoginDTO loginDTO) throws CustomException{
        validateLogin(loginDTO);

        UserDetailsDTO userDetailsDTO=userDetailsService.findByIdDTO(loginDTO.getLoginID());
        if(null ==userDetailsDTO){
            throw new CustomException(message.get("username.password.not.match"));
        }

        if(!passwordEncoder.matches(loginDTO.getPassword(),userDetailsDTO.getPassword())){
            throw new CustomException(message.get("username.password.not.match"));
        }
        logger.info("Login Validation Successful");
    }

    public void validateLogin(LoginDTO loginDTO) throws CustomException{
        Map<String,String> errors= new HashMap<>();
        if(!Utility.isValidString(loginDTO.getLoginID())){
            errors.put("loginID", message.get("loginid.required"));
        }
        if(!Utility.isValidString(loginDTO.getPassword())){
            errors.put("password", message.get("password.required"));
        }
        if(!errors.isEmpty()){
            throw new CustomException(message.get("enter.mandatory.fields"),errors);
        }
    }
}
