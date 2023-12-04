package com.microservices.user.dto.request;

import jakarta.validation.Valid;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RequestDto<T> {

    @Length(max = 36, message = "Max length is 36")
    @Length(min = 36, message = "Min length is 36")
    private String requestId;

    @Valid
    T data;
}
