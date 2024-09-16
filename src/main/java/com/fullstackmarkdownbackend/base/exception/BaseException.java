package com.fullstackmarkdownbackend.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * packageName    : com.fullstackmarkdownbackend.common.exception
 * fileName       : BaseException
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@Getter
public class BaseException extends RuntimeException {

    public final String message;
    public final HttpStatus httpStatus;
    public final LocalDateTime exceptionLocalDateTime;

    public BaseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.exceptionLocalDateTime = LocalDateTime.now();
    }

}
