package com.study.blog.account.application.command;

import com.study.blog.account.application.NotFoundRoleException;
import com.study.blog.account.application.RegisterException;
import com.study.blog.account.application.command.request.CreateUserRequest;
import com.study.blog.account.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private void validateRequest(CreateUserRequest request) {
        log.info("[validateRequest] - start validation");
        if (userRepository.existsByEmail(request.getEmail())) throw new RegisterException("error.service.register.duplicateEmail");
        if (!request.getPassword().equals(request.getPasswordConfirm())) throw new RegisterException("error.service.register.notMatchPassword");
    }

    private User makeUserEntity(String email, String name, String password) {
        log.info("[makeUserEntity] - email = {}, name = {}", email, name);
        return new User(email, passwordEncoder.encode(password), name);
    }

    private void mergeRole(User user, RoleType roleType) {
        log.info("[mergeRole] - add role");
        Role role = roleRepository.findByName(RoleType.find(roleType.getRole()).name()).orElseThrow(NotFoundRoleException::new);
        user.addRole(role);
    }


    @Transactional
    public UserDto createUser(CreateUserRequest request) {
        validateRequest(request);
        User user = makeUserEntity(request.getEmail(), request.getName(), request.getPassword());
        mergeRole(user, request.getRoleType());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }
}
