package com.jjcomp.aggropingpong.common.handler;

import com.jjcomp.aggropingpong.common.dto.ResponseData;
import com.jjcomp.aggropingpong.common.exception.GlobalNotFoundException;
import com.jjcomp.aggropingpong.common.exception.GlobalValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalNotFoundException.class)
    public ResponseEntity<?> handleGlobalNotFoundException(GlobalNotFoundException e) {
        log.error("GlobalExceptionHandler: {}", e.toString());
        return new ResponseEntity<>(ResponseData.notFound(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GlobalValidationException.class)
    public ResponseEntity<?> handleGlobalValidationException(GlobalValidationException e) {
        log.error("GlobalExceptionHandler: {}", e.toString());
        return new ResponseEntity<>(ResponseData.badRequest(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("GlobalExceptionHandler: {}", e.toString());
        return new ResponseEntity<>(ResponseData.error(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e) {
        log.error("GlobalExceptionHandler: {}", e.toString());
        return new ResponseEntity<>(ResponseData.error(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<?>> handleException(Exception e) {
        log.error("GlobalExceptionHandler: {}", e.toString());
        return new ResponseEntity<>(ResponseData.error(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
