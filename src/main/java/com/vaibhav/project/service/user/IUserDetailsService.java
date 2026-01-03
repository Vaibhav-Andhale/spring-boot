package com.vaibhav.project.service.user;

import com.vaibhav.project.dto.user.UserDetailsDTO;
import com.vaibhav.project.entities.user.UserDetails;
import com.vaibhav.project.exception.CustomException;

public interface IUserDetailsService {
    UserDetailsDTO findByIdDTO(String loginID) throws CustomException;
    UserDetailsDTO saveOrUpdate(UserDetailsDTO userDetailsDTO) throws CustomException;
}
