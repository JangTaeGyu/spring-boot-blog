package com.study.blog.account.application.query;

import com.study.blog.account.application.NotFoundUserException;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(String userCode) {
        return userRepository.findUserDetailsByCode(userCode).orElseThrow(NotFoundUserException::new);
    }
}
