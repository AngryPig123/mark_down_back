package com.fullstackmarkdownbackend.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.fullstackmarkdownbackend.event
 * fileName       : AuthenticationEvent
 * author         : AngryPig123
 * date           : 24. 9. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 18.        AngryPig123       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEvent {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        log.info("login successful for the user : {}", successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
        log.info("login failure for the user : {}, due to : {}", failureEvent.getAuthentication().getName(), failureEvent.getException().getMessage());
    }

}
