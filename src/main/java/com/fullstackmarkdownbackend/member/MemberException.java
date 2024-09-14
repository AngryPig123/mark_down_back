package com.fullstackmarkdownbackend.member;

import com.fullstackmarkdownbackend.common.exception.BaseException;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.fullstackmarkdownbackend.member
 * fileName       : MemberException
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@Getter
public class MemberException extends BaseException {

    private MemberException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
