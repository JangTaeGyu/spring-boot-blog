package com.study.blog.account.presentation;

import com.study.blog.account.application.query.LoginService;
import com.study.blog.account.application.query.data.JwtTokenData;
import com.study.blog.account.application.query.request.LoginRequest;
import com.study.blog.account.presentation.response.JwtTokenResponse;
import com.study.blog.springboot.exception.ValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "관리자 로그인")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = JwtTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content = @Content(schema = @Schema(implementation = ValidationResponse.class)))
    })
    @PostMapping
    public ResponseEntity<JwtTokenResponse> login(@RequestBody @Valid LoginRequest request) {
        log.info("[login] - {}", request);

        JwtTokenData tokenData = loginService.login(request);
        log.info("[register] - successful");

        JwtTokenResponse response = new JwtTokenResponse(tokenData);
        return ResponseEntity.ok(response);
    }
}
