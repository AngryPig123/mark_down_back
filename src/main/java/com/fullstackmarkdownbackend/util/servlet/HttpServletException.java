package com.fullstackmarkdownbackend.util.servlet;

import com.fullstackmarkdownbackend.base.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.fullstackmarkdownbackend.util.servlet
 * fileName       : HttpServletException
 * author         : AngryPig123
 * date           : 24. 9. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 21.        AngryPig123       최초 생성
 */
public class HttpServletException extends BaseException {

    public HttpServletException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
    
}
