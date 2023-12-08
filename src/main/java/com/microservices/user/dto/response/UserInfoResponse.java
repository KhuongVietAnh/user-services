package com.microservices.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;

    private String email;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
