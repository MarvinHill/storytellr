package de.storyteller.api.v1.auth;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Service for getting user information.
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Service("userService")
public class UserService {
    AuthUtils authUtils;

    /**
     * Get the id of the logged in user.
     * @return User id.
    */
    public String getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            return null;
        }
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.getSubject();
    }
}
