package com.fullstackmarkdownbackend.login.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fullstackmarkdownbackend.version.VersionConstants.VERSION;

/**
 * packageName    : com.fullstackmarkdownbackend.login.controller
 * fileName       : LoginTestController
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = VERSION + "/login/test")
public class LoginTestController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public ResponseEntity<String> handleLoginTest() {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
