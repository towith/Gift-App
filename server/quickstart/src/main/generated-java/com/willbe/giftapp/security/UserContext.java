/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 *
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/security/UserContext.p.vm.java
 */
package com.willbe.giftapp.security;

import com.willbe.giftapp.domain.User_;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

/**
 * Get Spring security context to access user data security infos
 */
public final class UserContext {
    public static final String ANONYMOUS_USER = "anonymousUser";

    private UserContext() {
    }

    /**
     * Tell whether the passed role is set?
     *
     * @return true if the passed role is present, false otherwise.
     */
    public static boolean hasRole(String roleName) {
        for (String role : getRoles()) {
            if (role.equalsIgnoreCase(roleName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return the current roles bound to the current thread by Spring Security.
     */
    public static List<String> getRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            return toStringList(auth.getAuthorities());
        }

        return emptyList();
    }

    public static List<String> toStringList(Iterable<? extends GrantedAuthority> grantedAuthorities) {
        List<String> result = newArrayList();

        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            result.add(grantedAuthority.getAuthority());
        }

        return result;
    }

    /**
     * Get the current username. Note that it may not correspond to a username that
     * currently exists in your account repository; it could be a spring security
     * 'anonymous user'.
     *
     * @return the current user's username, or 'anonymousUser'.
     * @see org.springframework.security.web.authentication.AnonymousAuthenticationFilter
     */
    public static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }

            return principal.toString();
        }

        return ANONYMOUS_USER;
    }

    public static User_ getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof User_) {
                return (User_) principal;
            }
        }

        return null;
    }

    public static Serializable getId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserWithId) {
                return ((UserWithId) principal).getId();
            }
        }

        return null;
    }

    /**
     * Retrieve the current UserDetails bound to the current thread by Spring Security, if any.
     */
    public static UserDetails getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal());
        }

        return null;
    }
}