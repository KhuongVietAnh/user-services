package com.microservices.user.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiRunTimeException extends RuntimeException {

    private String responseCode;

    private String description;

    public ApiRunTimeException(String code, String message) {
        super(message);
        this.responseCode = code;
    }

    public ApiRunTimeException(String code, String message, Throwable err) {
        super(message, err);
        this.responseCode = code;
    }
}
