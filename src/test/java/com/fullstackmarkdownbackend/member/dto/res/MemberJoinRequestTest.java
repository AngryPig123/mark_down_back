package com.fullstackmarkdownbackend.member.dto.res;

import com.fullstackmarkdownbackend.common.exception.BaseException;
import com.fullstackmarkdownbackend.member.dto.req.MemberJoinRequest;
import com.fullstackmarkdownbackend.util.validator.CommonValidatorException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.function.BiFunction;

import static com.fullstackmarkdownbackend.util.validator.CommonValidator.IS_NULL_MESSAGE;
import static com.fullstackmarkdownbackend.util.validator.CommonValidator.MUST_BE_EQUAL_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * packageName    : com.fullstackmarkdownbackend.member.dto.res
 * fileName       : MemberJoinRequestTest
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
class MemberJoinRequestTest {

    private MemberJoinRequest memberJoinRequest;

    private BiFunction<String, String, String> messageHelper = (field, validMessage) -> String.format("%s %s", field, validMessage);

    @BeforeEach
    void setUp() {
        memberJoinRequest = new MemberJoinRequest(
                "loginId", "email", "password", "password",
                "firstName", "lastName"
        );
    }

    @AfterEach
    void tearDown() {
        memberJoinRequest = new MemberJoinRequest(
                "loginId", "email", "password", "password",
                "firstName", "lastName"
        );
    }

    @Test
    void successTest() {
        Assertions.assertDoesNotThrow(() -> memberJoinRequest.valid());
    }

    @Test
    void loginIdIsNullTest() {

        memberJoinRequest.setLoginId(null);
        BaseException exception = assertThrows(CommonValidatorException.class, () -> {
            memberJoinRequest.valid();
        });

        String message = exception.getMessage();
        HttpStatus httpStatus = exception.getHttpStatus();

        Assertions.assertEquals(messageHelper.apply("loginId", IS_NULL_MESSAGE), message);
        Assertions.assertEquals(httpStatus, HttpStatus.BAD_REQUEST);

    }


    @Test
    void emailIsNullTest() {

        memberJoinRequest.setEmail(null);
        BaseException exception = assertThrows(CommonValidatorException.class, () -> {
            memberJoinRequest.valid();
        });

        String message = exception.getMessage();
        HttpStatus httpStatus = exception.getHttpStatus();

        Assertions.assertEquals(messageHelper.apply("email", IS_NULL_MESSAGE), message);
        Assertions.assertEquals(httpStatus, HttpStatus.BAD_REQUEST);

    }

    @Test
    void passwordIsNullTest() {

        memberJoinRequest.setPassword(null);
        BaseException exception = assertThrows(CommonValidatorException.class, () -> {
            memberJoinRequest.valid();
        });

        String message = exception.getMessage();
        HttpStatus httpStatus = exception.getHttpStatus();

        Assertions.assertEquals(messageHelper.apply("password", IS_NULL_MESSAGE), message);
        Assertions.assertEquals(httpStatus, HttpStatus.BAD_REQUEST);

    }

    @Test
    void passwordCheckIsNullTest() {

        memberJoinRequest.setPasswordCheck(null);
        BaseException exception = assertThrows(CommonValidatorException.class, () -> {
            memberJoinRequest.valid();
        });

        String message = exception.getMessage();
        HttpStatus httpStatus = exception.getHttpStatus();

        Assertions.assertEquals(messageHelper.apply("passwordCheck", IS_NULL_MESSAGE), message);
        Assertions.assertEquals(httpStatus, HttpStatus.BAD_REQUEST);

    }

    @Test
    void firstNameIsNullTest() {

        memberJoinRequest.setFirstName(null);
        BaseException exception = assertThrows(CommonValidatorException.class, () -> {
            memberJoinRequest.valid();
        });

        String message = exception.getMessage();
        HttpStatus httpStatus = exception.getHttpStatus();

        Assertions.assertEquals(messageHelper.apply("firstName", IS_NULL_MESSAGE), message);
        Assertions.assertEquals(httpStatus, HttpStatus.BAD_REQUEST);

    }

    @Test
    void lastNameIsNullTest() {

        memberJoinRequest.setLastName(null);
        BaseException exception = assertThrows(CommonValidatorException.class, () -> {
            memberJoinRequest.valid();
        });

        String message = exception.getMessage();
        HttpStatus httpStatus = exception.getHttpStatus();

        Assertions.assertEquals(messageHelper.apply("lastName", IS_NULL_MESSAGE), message);
        Assertions.assertEquals(httpStatus, HttpStatus.BAD_REQUEST);

    }

    @Test
    void passwordMustBeEqualTest() {

        memberJoinRequest.setPasswordCheck("not match password");
        BaseException exception = assertThrows(CommonValidatorException.class, () -> {
            memberJoinRequest.valid();
        });

        String message = exception.getMessage();
        HttpStatus httpStatus = exception.getHttpStatus();

        Assertions.assertEquals(messageHelper.apply("password", MUST_BE_EQUAL_MESSAGE), message);
        Assertions.assertEquals(httpStatus, HttpStatus.BAD_REQUEST);

    }

}