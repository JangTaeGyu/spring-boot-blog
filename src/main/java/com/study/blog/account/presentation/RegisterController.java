package com.study.blog.account.presentation;

import com.study.blog.account.application.command.RegisterService;
import com.study.blog.account.application.command.request.RegisterRequest;
import com.study.blog.springboot.exception.ValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "[임시] - 관리자 회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content = @Content(schema = @Schema(implementation = ValidationResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        log.info("[register] - {}", request);

        // registerService.register(request);
        log.info("[register] - successful");

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
