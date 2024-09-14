package com.fullstackmarkdownbackend.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * packageName    : com.fullstackmarkdownbackend.advice
 * fileName       : GlobalExceptionResponseBody
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@Getter
@AllArgsConstructor
public class GlobalExceptionResponseBody {

    private String url;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;

}
