package com.fullstackmarkdownbackend.util.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.fullstackmarkdownbackend.util.validator
 * fileName       : CommonValidatorTest
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */
class CommonValidatorTest {


    @Test
    void passwordRegexTest() {

        Assertions.assertThrows(CommonValidatorException.class, () -> CommonValidator.passwordRegexValid("1q2w3e4r"));
        Assertions.assertThrows(CommonValidatorException.class, () -> CommonValidator.passwordRegexValid("11111111"));
        Assertions.assertDoesNotThrow(() -> CommonValidator.passwordRegexValid("1q2w3e4Ar!@"));

    }

}
