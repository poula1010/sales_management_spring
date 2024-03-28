package com.poula.sales_management.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class APIException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
}
