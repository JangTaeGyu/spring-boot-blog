package com.study.blog.account.application.query;

import com.study.blog.account.domain.User;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.domain.UserMapper;
import com.study.blog.account.infrastructure.security.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInService {
    private CustomUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }

    public UserDto getLoggedUser() {
        CustomUserDetails userDetails = getUserDetails();
        User loggedUser = userDetails.getUser();
        return UserMapper.toDto(loggedUser);
    }
}
