package com.wiley.pdh.pdhproductionsupporter.controller.advisor;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.wiley.pdh.pdhproductionsupporter.exception.SysRootException;
import com.wiley.pdh.pdhproductionsupporter.model.ServiceError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class,
                       InvalidDefinitionException.class, IllegalArgumentException.class})
    public ServiceError onBadRequest(Exception e) {
        return new ServiceError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SysRootException.class)
    public ServiceError onConflictException(Exception e) {
        return new ServiceError(HttpStatus.CONFLICT.value(), e.getMessage());
    }
}
