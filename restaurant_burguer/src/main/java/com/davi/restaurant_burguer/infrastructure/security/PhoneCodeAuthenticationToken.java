package com.davi.restaurant_burguer.infrastructure.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PhoneCodeAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private Object credentials;

    public PhoneCodeAuthenticationToken(Object principal, Object crendentials) {
        super(null);
        this.principal = principal;
        this.credentials = crendentials;
        setAuthenticated(false);
    }

    public PhoneCodeAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
