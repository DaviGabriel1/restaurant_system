package com.davi.restaurant_burguer.infrastructure.security;

import com.davi.restaurant_burguer.models.Users;
import com.davi.restaurant_burguer.services.UserService;
import com.davi.restaurant_burguer.services.OtpService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class PhoneCodeAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final OtpService otpService;

    public PhoneCodeAuthenticationProvider(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone = authentication.getPrincipal().toString();
        String code = authentication.getCredentials().toString();

        if(!otpService.isValid(phone,code)) {
            throw new BadCredentialsException("código invalido ou expitado");
        }

        Users user = this.userService.getUserByPhone(phone);
        return new PhoneCodeAuthenticationToken(user,null, user.getGrantedAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
