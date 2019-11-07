package com.hoggen.testFrame.errors;


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
