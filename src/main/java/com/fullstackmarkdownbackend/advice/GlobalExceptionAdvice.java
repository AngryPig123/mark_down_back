package com.fullstackmarkdownbackend.advice;

import com.fullstackmarkdownbackend.base.exception.BaseException;
import com.fullstackmarkdownbackend.util.validator.CommonValidatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * packageName    : com.fullstackmarkdownbackend.advice
 * fileName       : GlobalExceptionAdvice
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CommonValidatorException.class)
    public ResponseEntity<GlobalExceptionResponseBody> handleCommonValidatorException(
            final CommonValidatorException exception,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(toCommonValidatorException(exception, webRequest), HttpStatus.BAD_REQUEST);
    }

    private GlobalExceptionResponseBody toCommonValidatorException(
            BaseException baseException, WebRequest webRequest
    ) {
        return new GlobalExceptionResponseBody(
                webRequest.getDescription(false),
                baseException.getHttpStatus(),
                baseException.getExceptionLocalDateTime(),
                baseException.getMessage()
        );
    }

}
