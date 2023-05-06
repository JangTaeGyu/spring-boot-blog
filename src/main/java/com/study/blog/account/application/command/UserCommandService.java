package com.study.blog.account.application.command;

import com.study.blog.account.application.NotFoundUserException;
import com.study.blog.account.application.command.request.CreateUserRequest;
import com.study.blog.account.application.command.request.UpdateUserRequest;
import com.study.blog.account.domain.User;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.domain.UserMapper;
import com.study.blog.account.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final RegisterSupport registerSupport;
    private final UserRepository userRepository;

    @Transactional
    public UserDto createUser(CreateUserRequest request) {
        registerSupport.validateRequest(request);
        User user = registerSupport.makeUserEntity(request.getEmail(), request.getName(), request.getPassword());
        registerSupport.mergeRole(user, request.getRoleType());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Transactional
    public UserDto updateUser(String userCode, UpdateUserRequest request) {
        User user = userRepository.findByCode(userCode).orElseThrow(NotFoundUserException::new);
        user.update(request.getName());
        return UserMapper.toDto(user);
    }

    @Transactional
    public void deleteUser(String userCode) {
        User user = userRepository.findByCode(userCode).orElseThrow(NotFoundUserException::new);
        user.delete();
    }
}
