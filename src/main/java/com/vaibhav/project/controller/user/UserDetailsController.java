package com.vaibhav.project.controller.user;

import com.vaibhav.project.dto.common.CustomResponseEntity;
import com.vaibhav.project.dto.user.UserDetailsDTO;
import com.vaibhav.project.service.user.IUserDetailsService;
import com.vaibhav.project.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserDetailsController {
    private final IUserDetailsService iUserDetailsService;
    private final Message message;

    @Autowired
    public UserDetailsController(IUserDetailsService iUserDetailsService,Message message) {
        this.message=message;
        this.iUserDetailsService = iUserDetailsService;
    }

    @PostMapping("/create")
    public CustomResponseEntity<UserDetailsDTO> createUser(@RequestBody UserDetailsDTO userDetailsDTO){
        return new CustomResponseEntity<>(message.get("record.saved"),null,iUserDetailsService.saveOrUpdate(userDetailsDTO));
    }

    @GetMapping("/get/{loginID}")
    public CustomResponseEntity<UserDetailsDTO> get(@PathVariable("loginID") String loginID){
        return new CustomResponseEntity<>(message.get("success"),null,iUserDetailsService.findByIdDTO(loginID));
    }
}
