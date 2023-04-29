package com.study.blog.account.presentation;

import com.study.blog.account.application.command.RegisterService;
import com.study.blog.account.application.command.request.RegisterRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "인증")
@Slf4j
@RestController
@RequestMapping("/api/admin/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        log.info("[register] - {}", request);

        registerService.register(request);
        log.info("[register] - successful");

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
