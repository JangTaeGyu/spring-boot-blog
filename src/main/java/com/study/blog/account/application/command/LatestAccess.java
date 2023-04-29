package com.study.blog.account.application.command;

import com.study.blog.account.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LatestAccess {
    private final UserRepository userRepository;

    @Transactional
    public void update(String email) {
        log.info("[update] - update user latest access");
        userRepository.updateLatestAccessByEmail(email);
    }
}
