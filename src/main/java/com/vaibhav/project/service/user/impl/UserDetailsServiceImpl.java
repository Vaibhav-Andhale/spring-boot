package com.vaibhav.project.service.user.impl;

import com.vaibhav.project.dto.user.UserDetailsDTO;
import com.vaibhav.project.entities.user.UserDetails;
import com.vaibhav.project.exception.CustomException;
import com.vaibhav.project.repository.user.IUserDetailsRepo;
import com.vaibhav.project.service.user.IUserDetailsService;
import com.vaibhav.project.utils.Message;
import com.vaibhav.project.utils.Utility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements IUserDetailsService {
    private final IUserDetailsRepo iUserDetailsRepo;
    private final PasswordEncoder passwordEncoder;
    private final Message message;

    @Autowired
    public UserDetailsServiceImpl(IUserDetailsRepo iUserDetailsRepo,PasswordEncoder passwordEncoder,Message message) {
        this.iUserDetailsRepo = iUserDetailsRepo;
        this.passwordEncoder=passwordEncoder;
        this.message=message;
    }

    @Transactional(readOnly = true)
    public UserDetails findById(String loginID) throws CustomException {
        return iUserDetailsRepo.findById(loginID).orElseThrow(() -> new CustomException("User Details not found"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDetails saveOrUpdate(UserDetails userDetails){
        return iUserDetailsRepo.save(userDetails);
    }

    public UserDetailsDTO findByIdDTO(String loginID) throws CustomException {
        UserDetails userDetails = findById(loginID);
        return toDTO(userDetails);
    }

    public UserDetailsDTO saveOrUpdate(UserDetailsDTO userDetailsDTO) throws CustomException{
        validateSaveOrUpdate(userDetailsDTO);
        UserDetails userDetails=toEntity(userDetailsDTO);
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userDetails=saveOrUpdate(userDetails);
        return toDTO(userDetails);
    }

    private UserDetailsDTO toDTO(UserDetails userDetails) throws CustomException{
        if(null == userDetails){
            throw new CustomException(message.get("entity.should.not.empty"));
        }
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        BeanUtils.copyProperties(userDetails, userDetailsDTO);
        return userDetailsDTO;
    }
    private UserDetails toEntity(UserDetailsDTO userDetailsDTO) throws CustomException{
        if(null == userDetailsDTO){
            throw new CustomException(message.get("dto.should.not.empty"));
        }
        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(userDetailsDTO, userDetails);
        return userDetails;
    }
    void validateSaveOrUpdate(UserDetailsDTO userDetailsDTO) throws CustomException{
        Map<String,String> errors= new HashMap<>();
        if(!Utility.isValidString(userDetailsDTO.getLoginID())){
            errors.put("loginID",message.get("loginid.required"));
        }
        if(!Utility.isValidString(userDetailsDTO.getUserName())){
            errors.put("userName",message.get("username.required"));
        }
        if(!Utility.isValidString(userDetailsDTO.getPassword())){
            errors.put("password",message.get("password.required"));
        }
        if(!errors.isEmpty()){
            throw new CustomException(message.get("record.save.failed"),errors);
        }
    }
}
