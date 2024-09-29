package com.fullstackmarkdownbackend.config.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.function.Supplier;

import static com.fullstackmarkdownbackend.constants.ApplicationConstants.CSRF_TOKEN_HEADER;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry.hadler
 * fileName       : ProjectCsrfTokenRequestAttributeHandler
 * author         : AngryPig123
 * date           : 24. 9. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 21.        AngryPig123       최초 생성
 */
@Slf4j
@Component
public class ProjectCsrfTokenRequestAttributeHandler extends CsrfTokenRequestAttributeHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       Supplier<CsrfToken> deferredCsrfToken) {
        String csrfTokenValue = request.getHeader(CSRF_TOKEN_HEADER);
        CsrfToken csrfToken = new SupplierCsrfToken(deferredCsrfToken);
        if (StringUtils.hasText(csrfTokenValue)) {
            request.setAttribute(CsrfToken.class.getName(), csrfToken);
            request.setAttribute(csrfToken.getParameterName(), csrfTokenValue);
        }
        request.setAttribute(HttpServletResponse.class.getName(), response);
        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        request.setAttribute(CSRF_TOKEN_HEADER, csrfToken);
    }


    private record SupplierCsrfToken(Supplier<CsrfToken> csrfTokenSupplier) implements CsrfToken {

        @Override
            public String getHeaderName() {
                return getDelegate().getHeaderName();
            }

            @Override
            public String getParameterName() {
                return getDelegate().getParameterName();
            }

            @Override
            public String getToken() {
                return getDelegate().getToken();
            }

            private CsrfToken getDelegate() {
                CsrfToken delegate = this.csrfTokenSupplier.get();
                if (delegate == null) {
                    throw new IllegalStateException("csrfTokenSupplier returned null delegate");
                }
                return delegate;
            }

        }

}
