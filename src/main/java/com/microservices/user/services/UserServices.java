package com.microservices.user.services;

import com.microservices.user.dto.request.RequestDto;
import com.microservices.user.dto.request.UserRegisterRequestDto;
import com.microservices.user.dto.response.ResponseDto;
import com.microservices.user.dto.response.UserInfoResponse;
import com.microservices.user.dto.response.UserRegisterResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface UserServices {
    ResponseEntity<ResponseDto<UserRegisterResponseDto>> register(RequestDto<UserRegisterRequestDto> userRegisterRequestDto);

    ResponseEntity<ResponseDto<List<UserInfoResponse>>> getAllUsers();
}
