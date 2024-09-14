package com.fullstackmarkdownbackend.test.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author shguddnr2@coremethod.co.kr
 * @version 1.0
 * @since 24. 9. 13.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestRequest {

    private String name;
    private String content;

}
