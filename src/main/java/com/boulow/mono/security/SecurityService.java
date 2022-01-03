package com.boulow.mono.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.boulow.mono.config.BoulowProperties;
import com.boulow.mono.entity.User;
import com.boulow.mono.security.utils.CookieUtils;
import com.boulow.mono.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Service
public class SecurityService {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    private BoulowProperties boulowProperties;

    public User getUser() {
        User userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof User) {
            userPrincipal = ((User) principal);
        }
        return userPrincipal;
    }

    public Credentials getCredentials() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (Credentials) securityContext.getAuthentication().getCredentials();
    }

    public boolean isPublic() {
        return boulowProperties.getSecurity().getAllowedPublicApis().contains(httpServletRequest.getRequestURI());
    }

    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            bearerToken = authorization.substring(7);
        }
        return bearerToken;
    }
    
}
