package com.study.blog.account.presentation;

import com.study.blog.account.application.query.LoginService;
import com.study.blog.account.application.query.data.JwtTokenData;
import com.study.blog.account.application.query.request.LoginRequest;
import com.study.blog.account.presentation.response.JwtTokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "인증")
@Slf4j
@RestController
@RequestMapping("/api/admin/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<JwtTokenResponse> login(@RequestBody @Valid LoginRequest request) {
        log.info("[login] - {}", request);

        JwtTokenData tokenData = loginService.login(request);
        log.info("[register] - successful");

        JwtTokenResponse response = new JwtTokenResponse(tokenData);
        return ResponseEntity.ok(response);
    }
}
