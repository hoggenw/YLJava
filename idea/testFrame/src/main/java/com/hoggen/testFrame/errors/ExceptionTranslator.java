package com.hoggen.testFrame.errors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常统一处理
 *
 */
//我们只需要在Controller中抛出Exception，当然我们可能会有多种不同的Exception
// 。然后在@ControllerAdvice类中，根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理。
@ControllerAdvice
@RestController
@Slf4j
public class ExceptionTranslator {
    private static final String ERROR_LOG_FORMAT = "Exception  with cause = \\'{}\\' and exception = \\'{}\\'";
    /**
     * 数据已经存在错误统一处理
     *
     * @param ex 错误类型
     * @return 错误信息
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage dataHasExistExceptionHandler(BusinessException ex) {
        log.error(ERROR_LOG_FORMAT, ex.getMessage(), ex);
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    /**
     * 数据已经存在错误统一处理
     *
     * @param ex 错误类型
     * @return 错误信息
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage dataNotFindExceptionHandler(DataNotFoundException ex) {
        log.error(ERROR_LOG_FORMAT, ex.getMessage(), ex);
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

}
