package com.kykj.internethospital.express.web.rest.errors;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author baogang
 */
@AllArgsConstructor
@Getter
public class ErrorMessage {
    private String exception;
    private String message;
}
