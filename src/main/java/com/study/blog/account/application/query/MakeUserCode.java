package com.study.blog.account.application.query;

import com.study.blog.account.domain.UserRepository;
import com.study.blog.springboot.util.RandomCodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakeUserCode {
    private static final String PREFIX = "U";

    private final UserRepository userRepository;

    public String next() {
        String code;
        do {
            code = PREFIX + RandomCodeUtils.makeCode();
        } while (userRepository.existsByCode(code));
        return code;
    }
}
