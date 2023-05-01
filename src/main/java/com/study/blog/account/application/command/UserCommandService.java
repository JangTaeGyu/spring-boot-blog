package com.study.blog.account.application.command;

import com.study.blog.account.application.command.request.CreateUserRequest;
import com.study.blog.account.domain.RoleRepository;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDto createUser(CreateUserRequest request) {
        return null;
    }
}
