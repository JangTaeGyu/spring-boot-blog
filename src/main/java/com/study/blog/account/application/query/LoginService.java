package com.study.blog.account.application.query;

import com.study.blog.account.application.DeletedUserException;
import com.study.blog.account.application.WithdrawalUserException;
import com.study.blog.account.application.command.LatestAccess;
import com.study.blog.account.application.query.data.JwtTokenData;
import com.study.blog.account.application.query.request.LoginRequest;
import com.study.blog.account.domain.User;
import com.study.blog.account.infrastructure.security.jwt.JwtTokenProvider;
import com.study.blog.account.infrastructure.security.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final LatestAccess latestAccess;

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

    private JwtTokenData makeJwtTokenData(String email) {
        log.info("[makeJwtTokenData] - make user's jwt token");
        String jwtToken = jwtTokenProvider.generateToken(email);
        Long expireTime = jwtTokenProvider.expireTime(null);
        return new JwtTokenData(jwtToken, expireTime);
    }

    @Transactional
    public JwtTokenData login(LoginRequest request) {
        Authentication authentication = getAuthentication(request.getEmail(), request.getPassword());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User checkedUser = checkUserStatus(userDetails.getUser());
        latestAccess.update(checkedUser.getEmail());
        return makeJwtTokenData(checkedUser.getEmail());
    }
}
