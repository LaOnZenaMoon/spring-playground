package me.lozm.global.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiCommonAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public String handleBaseException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public String handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return e.getMessage();
    }

}
