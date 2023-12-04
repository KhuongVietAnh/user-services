package com.microservices.user.controllers;

import com.microservices.user.dto.request.RequestDto;
import com.microservices.user.dto.request.UserRegisterRequestDto;
import com.microservices.user.dto.response.ResponseDto;
import com.microservices.user.dto.response.UserInfoResponse;
import com.microservices.user.dto.response.UserRegisterResponseDto;
import com.microservices.user.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserControllers {

    @Autowired
    UserServices userServices;

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseDto<UserRegisterResponseDto>> register(@RequestBody @Valid RequestDto<UserRegisterRequestDto> userRegisterRequestDto) {

        return userServices.register(userRegisterRequestDto);
    }

    @GetMapping(path = "/get-users")
    public ResponseEntity<ResponseDto<List<UserInfoResponse>>> getAllyUsers() {

        return userServices.getAllUsers();
    }
}
