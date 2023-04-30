package com.study.blog.account.domain;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RoleType {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),
    UNKNOWN("UNKNOWN");

    private static final Map<String, RoleType> services =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(RoleType::getRole, Function.identity())));
    @Getter
    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    public static Set<RoleType> toSet() {
        return new HashSet<>(services.values());
    }

    public static RoleType find(String name) {
        return Optional.ofNullable(services.get(name)).orElse(UNKNOWN);
    }
}
