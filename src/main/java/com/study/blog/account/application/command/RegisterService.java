package com.study.blog.account.application.command;

import com.study.blog.account.application.NotFoundRoleException;
import com.study.blog.account.application.RegisterException;
import com.study.blog.account.application.command.request.RegisterRequest;
import com.study.blog.account.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private void validateRequest(RegisterRequest request) {
        log.info("[validateRequest] - start validation");
        if (userRepository.existsByEmail(request.getEmail())) throw new RegisterException("error.service.register.duplicateEmail");
        if (!request.getPassword().equals(request.getPasswordConfirm())) throw new RegisterException("error.service.register.notMatchPassword");
    }

    private User makeUserEntity(String email, String name, String password) {
        log.info("[makeUserEntity] - email = {}, name = {}", email, name);
        return new User(email, passwordEncoder.encode(password), name);
    }

    private void mergeAdminRole(User user) {
        log.info("[mergeAdminRole] - add admin rule");
        Role role = roleRepository.findByName(RoleType.find("ADMIN").name()).orElseThrow(NotFoundRoleException::new);
        user.addRole(role);
    }

    @Transactional
    public void register(RegisterRequest request) {
        validateRequest(request);
        User user = makeUserEntity(request.getEmail(), request.getName(), request.getPassword());
        mergeAdminRole(user);
        userRepository.save(user);
    }
}
