package com.study.blog.account.presentation;

import com.study.blog.account.application.command.UserCommandService;
import com.study.blog.account.application.command.request.CreateUserRequest;
import com.study.blog.account.application.command.request.UpdateUserRequest;
import com.study.blog.account.application.query.UserService;
import com.study.blog.account.domain.UserDto;
import com.study.blog.account.presentation.response.UserResponse;
import com.study.blog.springboot.constant.OpenApiConstant;
import com.study.blog.springboot.exception.ValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "회원 관리")
@Slf4j
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserCommandService userCommandService;
    private final UserService userService;

    @Operation(
            summary = "회원 등록",
            security = @SecurityRequirement(name = OpenApiConstant.SECURITY_NAME))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content = @Content(schema = @Schema(implementation = ValidationResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid CreateUserRequest request) {
        log.info("[create] - {}", request);

        UserDto user = userCommandService.createUser(request);
        log.info("[create] - successful");

        UserResponse response = new UserResponse(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "회원 상세보기",
            security = @SecurityRequirement(name = OpenApiConstant.SECURITY_NAME))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{userCode}")
    public ResponseEntity<UserResponse> show(@PathVariable String userCode) {
        log.info("[show] - user code = {}", userCode);

        UserDto user = userService.getUser(userCode);
        log.info("[show] - successful");

        UserResponse response = new UserResponse(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "회원 수정",
            security = @SecurityRequirement(name = OpenApiConstant.SECURITY_NAME))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content = @Content(schema = @Schema(implementation = ValidationResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{userCode}")
    public ResponseEntity<UserResponse> update(
            @PathVariable String userCode,
            @RequestBody @Valid UpdateUserRequest request) {
        log.info("[update] - user code = {}, {}", userCode, request);

        UserDto user = userCommandService.updateUser(userCode, request);
        log.info("[update] - successful");

        UserResponse response = new UserResponse(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "회원 삭제",
            security = @SecurityRequirement(name = OpenApiConstant.SECURITY_NAME))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{userCode}")
    public ResponseEntity<Void> delete(@PathVariable String userCode) {
        log.info("[delete] - user code = {}", userCode);

        userCommandService.deleteUser(userCode);
        log.info("[delete] - successful");

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
