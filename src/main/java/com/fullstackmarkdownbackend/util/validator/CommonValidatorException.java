package com.fullstackmarkdownbackend.util.validator;

import com.fullstackmarkdownbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.fullstackmarkdownbackend.util.validator
 * fileName       : CommonRequestValidatorException
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
public class CommonValidatorException extends BaseException {

    protected CommonValidatorException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
