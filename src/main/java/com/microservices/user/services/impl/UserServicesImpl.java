package com.microservices.user.services.impl;

import com.microservices.user.contants.Contanst;
import com.microservices.user.dto.request.RequestDto;
import com.microservices.user.dto.request.UserRegisterRequestDto;
import com.microservices.user.dto.response.ResponseDto;
import com.microservices.user.dto.response.UserInfoResponse;
import com.microservices.user.dto.response.UserRegisterResponseDto;
import com.microservices.user.enums.RoleEnum;
import com.microservices.user.exception.ApiRunTimeException;
import com.microservices.user.model.Roles;
import com.microservices.user.model.Users;
import com.microservices.user.repository.RoleRepository;
import com.microservices.user.repository.UserRepository;
import com.microservices.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseDto<UserRegisterResponseDto>> register(RequestDto<UserRegisterRequestDto> userRegisterRequestDto) {
        ResponseDto<UserRegisterResponseDto> responseDto = new ResponseDto<>();

        if (userRepository.existsByEmail(userRegisterRequestDto.getData().getEmail())) {
            responseDto.setResponseCode(Contanst.EXISTED);
            responseDto.setDescription("Email is already taken!");
            return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
        }

        String asignRoles = userRegisterRequestDto.getData().getRole();

        Users users = new Users();
        users.setEmail(userRegisterRequestDto.getData().getEmail());
        users.setPassword(userRegisterRequestDto.getData().getPassword());

        Roles roles;
        // If role is null then set default is USER
        if (asignRoles != null) {
            roles = roleRepository.findByRoleName(RoleEnum.USER)
                    .orElseThrow(() -> new ApiRunTimeException(Contanst.NOT_FOUND, "Role is not found."));
        } else {
            switch (asignRoles) {
                case "admin":
                    roles = roleRepository.findByRoleName(RoleEnum.ADMIN)
                            .orElseThrow(() -> new ApiRunTimeException(Contanst.NOT_FOUND, "Role is not found."));
                    break;
                default:
                    roles = roleRepository.findByRoleName(RoleEnum.USER)
                            .orElseThrow(() -> new ApiRunTimeException(Contanst.NOT_FOUND, "Role is not found."));
            }
        }

        users.setRoles(roles);
        userRepository.save(users);

        responseDto.setDescription("Create account success.");
        responseDto.setResponseCode(Contanst.SUCCESS);
        responseDto.setData(new UserRegisterResponseDto(users.getEmail()));

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<List<UserInfoResponse>>> getAllUsers() {
        List<Users> usersList = userRepository.findAll();

        ResponseDto<List<UserInfoResponse>> responseDto = new ResponseDto<>();

        List<UserInfoResponse> userInfoResponses = usersList.stream()
                .map(data -> new UserInfoResponse(data.getId(), data.getEmail(), data.getCreateAt(), data.getUpdateAt()))
                .collect(Collectors.toList());

        responseDto.setDescription("Get data success.");
        responseDto.setResponseCode(Contanst.SUCCESS);
        responseDto.setData(userInfoResponses);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
