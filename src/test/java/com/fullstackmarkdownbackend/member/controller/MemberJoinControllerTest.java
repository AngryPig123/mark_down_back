package com.fullstackmarkdownbackend.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackmarkdownbackend.member.dto.req.MemberJoinRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * packageName    : com.fullstackmarkdownbackend.member.controller
 * fileName       : MemberJoinControllerTest
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@SpringBootTest
@AutoConfigureMockMvc
class MemberJoinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MemberJoinRequest memberJoinRequest;

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
    void controllerTest() throws Exception {
        memberJoinRequest.setPasswordCheck(null);
        mockMvc.perform(
                        post("/api/v1/member/join")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(memberJoinRequest))
                )
                .andDo(print());
    }

}