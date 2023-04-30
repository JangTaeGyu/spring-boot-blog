package com.study.blog.springboot.runner;

import com.study.blog.account.application.command.RegisterService;
import com.study.blog.account.application.command.request.RegisterRequest;
import com.study.blog.account.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminUserDataRunner implements CommandLineRunner {
    private final RegisterService registerService;
    private final UserRepository userRepository;

    private RegisterRequest makeRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("admin");
        request.setName("관리자");
        request.setPassword("1234");
        request.setPasswordConfirm("1234");
        return request;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("")) {
            registerService.register(makeRegisterRequest());
        }
    }
}
