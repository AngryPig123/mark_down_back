package com.fullstackmarkdownbackend.util.encoder;

import com.fullstackmarkdownbackend.base.vo.EncodedType;
import com.fullstackmarkdownbackend.base.vo.Password;

/**
 * packageName    : com.fullstackmarkdownbackend.util.encoder
 * fileName       : EncoderService
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */
public interface EncoderUtil {

    Password passwordEncryption(EncodedType encodedType, String password);

}
