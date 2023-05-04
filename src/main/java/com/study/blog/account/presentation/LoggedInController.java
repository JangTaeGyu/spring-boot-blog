package com.study.blog.account.presentation;

import com.study.blog.account.application.query.LoggedInService;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.presentation.response.UserResponse;
import com.study.blog.springboot.constant.OpenApiConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증")
@Slf4j
@RestController
@RequestMapping("/api/admin/me")
@RequiredArgsConstructor
public class LoggedInController {
    private final LoggedInService loggedInService;

    @Operation(
            summary = "로그인된 회원 정보",
            security = @SecurityRequirement(name = OpenApiConstant.SECURITY_NAME))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<UserResponse> me() {
        log.info("[me]");

        UserDto user = loggedInService.getLoggedUser();
        log.info("[me] - successful");

        UserResponse response = new UserResponse(user);
        return ResponseEntity.ok(response);
    }
}
