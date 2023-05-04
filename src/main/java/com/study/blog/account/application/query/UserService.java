package com.study.blog.account.application.query;

import com.study.blog.account.application.NotFoundUserException;
import com.study.blog.account.domain.User;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.domain.UserMapper;
import com.study.blog.account.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(String userCode) {
        User user = userRepository.findByCode(userCode).orElseThrow(NotFoundUserException::new);
        return UserMapper.toDto(user);
    }
}
