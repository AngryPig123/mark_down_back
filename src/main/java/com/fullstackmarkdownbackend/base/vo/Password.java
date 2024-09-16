package com.fullstackmarkdownbackend.base.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.fullstackmarkdownbackend.common.vo
 * fileName       : Password
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
public class Password {

    private final String encodedPassword;
    private final EncodedType encodedType;

}
