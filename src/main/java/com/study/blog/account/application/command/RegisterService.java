package com.study.blog.account.application.command;

import com.study.blog.account.application.command.request.RegisterRequest;
import com.study.blog.account.domain.RoleType;
import com.study.blog.account.domain.User;
import com.study.blog.account.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final RegisterSupport registerSupport;
    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterRequest request) {
        registerSupport.validateRequest(request);
        User user = registerSupport.makeUserEntity(request.getEmail(), request.getName(), request.getPassword());
        registerSupport.mergeRole(user, RoleType.ROLE_ADMIN);
        userRepository.save(user);
    }
}
