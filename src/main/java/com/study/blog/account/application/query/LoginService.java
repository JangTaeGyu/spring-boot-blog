package com.study.blog.account.application.query;

import com.study.blog.account.application.DeletedUserException;
import com.study.blog.account.application.WithdrawalUserException;
import com.study.blog.account.application.query.request.LoginRequest;
import com.study.blog.account.domain.User;
import com.study.blog.account.infrastructure.security.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;

    private Authentication getAuthentication(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        log.info("[getAuthentication] - find user by email and password");
        return authenticationManager.authenticate(authenticationToken);
    }

    private User checkUserStatus(User user) {
        log.info("[checkUserStatus] - check user status");
        if (user.getWithdrawalAt() != null) throw new WithdrawalUserException();
        if (user.getDeletedAt() != null) throw new DeletedUserException();
        return user;
    }

    public Object login(LoginRequest request) {
        Authentication authentication = getAuthentication(request.getEmail(), request.getPassword());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User checkedUser = checkUserStatus(userDetails.getUser());

        return null;
    }
}
