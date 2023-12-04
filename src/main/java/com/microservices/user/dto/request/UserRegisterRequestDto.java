package com.microservices.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegisterRequestDto {

    @NotBlank(message = "email is not null")
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "email invalid format")
    private String email;

    @NotBlank(message = "password is not null")
    private String password;

    @NotBlank(message = "role is not null")
    private String role;
}
