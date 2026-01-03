package com.vaibhav.project.controller.login;

import com.vaibhav.project.dto.common.CustomResponseEntity;
import com.vaibhav.project.dto.login.LoginDTO;
import com.vaibhav.project.exception.CustomException;
import com.vaibhav.project.service.login.ILoginService;
import com.vaibhav.project.utils.Message;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private static final Logger logger= LoggerFactory.getLogger(LoginController.class);

    private ILoginService loginService;

    private final Message message;

    @Autowired
    public LoginController(ILoginService loginService,Message message) {
        this.loginService = loginService;
        this.message=message;
    }

    @PostMapping("/login")
    public CustomResponseEntity<Object> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        loginService.login(loginDTO);
        /*String accessToken = jwtService.generateAccessToken(loginDTO.getLoginID());
        String refreshToken = jwtService.generateRefreshToken(loginDTO.getLoginID());
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/auth/refreshToken")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());*/
        return new CustomResponseEntity<>(message.get("success"),null,"accessToken");
    }

    @PostMapping("/refreshToken")
    public CustomResponseEntity<String> refreshToken(HttpServletRequest request) {

        if(null == request.getCookies()){
            throw new CustomException("Cookies not found");
        }
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new CustomException("Refresh token missing"));

        /*String username = jwtService.extractUsername(refreshToken);
        UserDetails user= Utility.loadUserByUsername();

        if(user.getUsername().equals(username)){
            throw new CustomException("Invalid Refresh token");
        }

        if (!jwtService.isValid(refreshToken, user.getUsername()))
            throw new CustomException("Refresh token expired");

        String newAccessToken = jwtService.generateAccessToken(user.getUsername());
*/
        return new CustomResponseEntity<>(message.get("success"),null,"newAccessToken");
    }
}
