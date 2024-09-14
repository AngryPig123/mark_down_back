package com.fullstackmarkdownbackend.test.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shguddnr2@coremethod.co.kr
 * @version 1.0
 * @since 24. 9. 3.
 */

@Slf4j
@RestController
@RequestMapping(path = "/api/security/test")
public class SecurityTestController {

    @GetMapping
    public ResponseEntity<Success> corsCheckApi() {
        return new ResponseEntity<>(new Success("success"), HttpStatus.OK);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Success {
        private String message;
    }

}
