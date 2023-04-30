package com.study.blog.springboot.runner;

import com.study.blog.account.application.command.RegisterService;
import com.study.blog.account.application.command.request.RegisterRequest;
import com.study.blog.account.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminUserDataRunner implements CommandLineRunner {
    private final RegisterService registerService;
    private final UserRepository userRepository;
    private final String adminName;
    private final String adminEmail;
    private final String adminPassword;

    public AdminUserDataRunner(RegisterService registerService,
                               UserRepository userRepository,
                               @Value("${admin.user.name}") String adminName,
                               @Value("${admin.user.email}") String adminEmail,
                               @Value("${admin.user.password}") String adminPassword) {
        this.registerService = registerService;
        this.userRepository = userRepository;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    private RegisterRequest makeRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail(adminEmail);
        request.setName(adminName);
        request.setPassword(adminPassword);
        request.setPasswordConfirm(adminPassword);
        return request;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail(adminEmail)) {
            registerService.register(makeRegisterRequest());
        }
    }
}
