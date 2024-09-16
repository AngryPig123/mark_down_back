package com.fullstackmarkdownbackend.util.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fullstackmarkdownbackend.util.regex.CommonRegex.PASSWORD_REGEX;

/**
 * packageName    : com.fullstackmarkdownbackend.common.validator
 * fileName       : CommonRequestValueValidator
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonValidator {

    public static String IS_NULL_MESSAGE = "is null";
    public static String MUST_BE_EQUAL_MESSAGE = "must be equal";
    public static String NOT_FOUND_MESSAGE = "is not found";
    public static String CONFLICT_MESSAGE = "is conflict";

    private static final BiFunction<String, HttpStatus, CommonValidatorException> EXCEPTION_SUPPLIER =
            CommonValidatorException::new;

    private static final BiFunction<String, String, String> MESSAGE_HELPER =
            (field, validMessage) -> String.format("%s %s", field, validMessage);

    public static void isNull(Object object, String field) {
        if (Objects.isNull(object)) {
            String exceptionMessage = MESSAGE_HELPER.apply(field, IS_NULL_MESSAGE);
            throw EXCEPTION_SUPPLIER.apply(exceptionMessage, HttpStatus.BAD_REQUEST);
        }
    }

    public static void notEquals(Object o1, Object o2, String field) {
        if (!Objects.equals(o1, o2)) {
            String exceptionMessage = MESSAGE_HELPER.apply(field, MUST_BE_EQUAL_MESSAGE);
            throw EXCEPTION_SUPPLIER.apply(exceptionMessage, HttpStatus.BAD_REQUEST);
        }
    }

    //  비어있으면 에러를 반환한다.
    public static <T> T optionalEmptyException(Optional<T> optional, String objectName) {
        if (optional.isEmpty()) {
            String exceptionMessage = MESSAGE_HELPER.apply(objectName, NOT_FOUND_MESSAGE);
            throw EXCEPTION_SUPPLIER.apply(exceptionMessage, HttpStatus.NOT_FOUND);
        } else {
            return optional.get();
        }
    }

    //  존재하면 에러를 반환한다.
    public static <T> void optionalIsPresentException(Optional<T> optional, String objectName) {
        if (optional.isPresent()) {
            String exceptionMessage = MESSAGE_HELPER.apply(objectName, CONFLICT_MESSAGE);
            throw EXCEPTION_SUPPLIER.apply(exceptionMessage, HttpStatus.CONFLICT);
        }
    }

    public static void passwordRegexValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX.getRegex());
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            String exceptionMessage = MESSAGE_HELPER.apply("password", PASSWORD_REGEX.getMessage());
            throw EXCEPTION_SUPPLIER.apply(exceptionMessage, HttpStatus.BAD_REQUEST);
        }
    }

}
