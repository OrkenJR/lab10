package kz.iitu.itse_1904.naga.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleBadRequestException
            (MethodArgumentTypeMismatchException ex, WebRequest request)
    {
        return new ResponseEntity<>("Your request is not correct", HttpStatus.NOT_FOUND);
    }


}
