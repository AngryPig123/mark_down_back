package com.fullstackmarkdownbackend.config;

import com.fullstackmarkdownbackend.base.vo.EncodedType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * packageName    : com.fullstackmarkdownbackend.config
 * fileName       : PasswordFactory
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
public class PasswordFactory {

    private static final Map<EncodedType, PasswordEncoder> encoders = new HashMap<>();

    static {
        encoders.put(EncodedType.BCRYPT, new BCryptPasswordEncoder());
        encoders.put(EncodedType.NOOP, NoOpPasswordEncoder.getInstance());
        encoders.put(EncodedType.SCRYPT, SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
    }

    public PasswordEncoder passwordEncoderFactory(EncodedType encodedType) {
        PasswordEncoder passwordEncoder = encoders.get(encodedType);
        if (Objects.isNull(passwordEncoder)) {
            throw new RuntimeException("암호화 방식을 찾을 수 없습니다.");
        }
        return passwordEncoder;
    }

}
