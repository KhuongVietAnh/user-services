package com.microservices.user.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiException extends Exception {
    private String responseCode;

    private String description;

    public ApiException(String code) {
        this.responseCode = code;
    }

    public ApiException(String code, String message) {
        super(message);
        this.responseCode = code;
    }

    public ApiException(String code, String message, Throwable err) {
        super(message, err);
        this.responseCode = code;
    }
}
