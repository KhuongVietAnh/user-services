package com.microservices.user.handle_exception;

import com.microservices.user.dto.HandleResponseErrorDto;
import com.microservices.user.dto.response.ResponseDto;
import com.microservices.user.exception.ApiException;
import com.microservices.user.exception.ApiRunTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class HandleException {

    @ExceptionHandler(ApiException.class)
    public HandleResponseErrorDto handleAllException(Exception ex, WebRequest request) {
        ApiException apiException = (ApiException) ex;
        return new HandleResponseErrorDto(apiException.getResponseCode(), apiException.getLocalizedMessage(), request.getContextPath());
    }

    @ExceptionHandler(ApiRunTimeException.class)
    public HandleResponseErrorDto handleRunTimeException(Exception ex, WebRequest request) {
        ApiRunTimeException apiRunTimeException = (ApiRunTimeException) ex;
        return new HandleResponseErrorDto(apiRunTimeException.getResponseCode(), apiRunTimeException.getLocalizedMessage(), request.getContextPath());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public HandleResponseErrorDto handleAuthenticationException(Exception ex, WebRequest request) {
        return new HandleResponseErrorDto(HttpStatus.UNAUTHORIZED + "", ex.getLocalizedMessage(), request.getContextPath());
    }

    @ExceptionHandler(BindException.class)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleBindException(BindException e) {
        if (e.getBindingResult().hasErrors())
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String defaultMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        String fieldError = e.getBindingResult().getFieldError().getField();

        return new ResponseDto("400", defaultMsg, null);
    }
}
