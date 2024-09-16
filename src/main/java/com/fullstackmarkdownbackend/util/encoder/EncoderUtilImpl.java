package com.fullstackmarkdownbackend.util.encoder;

import com.fullstackmarkdownbackend.base.vo.EncodedType;
import com.fullstackmarkdownbackend.base.vo.Password;
import com.fullstackmarkdownbackend.config.PasswordFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.fullstackmarkdownbackend.util.encoder
 * fileName       : EncoderServiceImpl
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EncoderUtilImpl implements EncoderUtil {

    private final PasswordFactory passwordFactory;

    @Override
    public Password passwordEncryption(EncodedType encodedType, String password) {
        PasswordEncoder passwordEncoder = passwordFactory.passwordEncoderFactory(EncodedType.BCRYPT);
        return new Password(passwordEncoder.encode(password), encodedType);
    }

}
