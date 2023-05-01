package com.study.blog.account.presentation;

import com.study.blog.account.application.command.UserCommandService;
import com.study.blog.account.application.command.request.CreateUserRequest;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.presentation.response.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "회원 관리")
@Slf4j
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserCommandService userCommandService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest request) {
        log.info("[create] - {}", request);

        UserDto user = userCommandService.createUser(request);
        log.info("[create] - successful");

        UserResponse response = new UserResponse(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
