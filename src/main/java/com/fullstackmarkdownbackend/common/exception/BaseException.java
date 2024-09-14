package com.fullstackmarkdownbackend.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

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
