package com.fullstackmarkdownbackend.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackmarkdownbackend.login.dto.req.MemberLoginRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.plugins.MockMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * packageName    : com.fullstackmarkdownbackend.login.controller
 * fileName       : LoginControllerTest
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginTest() throws Exception {

        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(
                "admin", "1q2w3e4R!@"
        );

        mockMvc.perform(
                        post("/api/v1/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberLoginRequest))
                )
                .andDo(print());

    }

}
