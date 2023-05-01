package com.study.blog.account.application.command;

import com.study.blog.account.application.NotFoundRoleException;
import com.study.blog.account.application.RegisterException;
import com.study.blog.account.application.command.request.RegisterRequest;
import com.study.blog.account.application.query.MakeUserCode;
import com.study.blog.account.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterSupport {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MakeUserCode makeUserCode;

    public void validateRequest(RegisterRequest request) {
        log.info("[validateRequest] - start validation");
        if (userRepository.existsByEmail(request.getEmail())) throw new RegisterException("error.service.register.duplicateEmail");
        if (!request.getPassword().equals(request.getPasswordConfirm())) throw new RegisterException("error.service.register.notMatchPassword");
    }

    public User makeUserEntity(String email, String name, String password) {
        log.info("[makeUserEntity] - email = {}, name = {}", email, name);
        return new User(makeUserCode.next(),
                email,
                passwordEncoder.encode(password),
                name);
    }

    public void mergeRole(User user, RoleType roleType) {
        log.info("[mergeRole] - add role");
        Role role = roleRepository.findByName(RoleType.find(roleType.getRole()).name()).orElseThrow(NotFoundRoleException::new);
        user.addRole(role);
    }
}
