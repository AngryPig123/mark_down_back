package com.fullstackmarkdownbackend.test.controller;

import com.fullstackmarkdownbackend.test.dto.TestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shguddnr2@coremethod.co.kr
 * @version 1.0
 * @since 24. 9. 13.
 */
@Slf4j
@RestController
@RequestMapping(path = "/test")
public class TestController {

    @PostMapping
    public ResponseEntity<TestRequest> formPostMappingTest(@RequestBody TestRequest testRequest) {
        return new ResponseEntity<>(testRequest, HttpStatus.OK);
    }

}
