package com.fullstackmarkdownbackend.util.regex;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.fullstackmarkdownbackend.util.regex
 * fileName       : CommonRegex
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */
@Getter
@AllArgsConstructor
public enum CommonRegex {

    PASSWORD_REGEX(
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,15}$",
            "최소 8 자 및 최대 15 자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상"
    );

    private final String regex;
    private final String message;

}
