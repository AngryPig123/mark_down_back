package com.fullstackmarkdownbackend.token.dto.res;

import com.fullstackmarkdownbackend.base.vo.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.fullstackmarkdownbackend.provider.token.dto.res
 * fileName       : TokenResponse
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */
@Getter
@AllArgsConstructor
public class TokenResponse {
    private TokenType tokenType;
    private String tokenValue;
}
