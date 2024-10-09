package com.fullstackmarkdownbackend.config.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fullstackmarkdownbackend.version.VersionConstants.VERSION;

/**
 * packageName    : com.fullstackmarkdownbackend.config.security
 * fileName       : SecurityTokenController
 * author         : AngryPig123
 * date           : 24. 10. 9.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 9.        AngryPig123       최초 생성
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = VERSION + "/security")
public class SecurityTokenController {

    @GetMapping(path = "/csrf")
    public ResponseEntity<String> csrfSupplierHandler(HttpServletRequest httpServletRequest) {
        CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());
        log.info("csrfToken = {}", csrfToken);
        return new ResponseEntity<>(csrfToken.getToken(), HttpStatus.OK);
    }

}
