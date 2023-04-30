package com.study.blog.springboot.runner;

import com.study.blog.account.domain.Role;
import com.study.blog.account.domain.RoleRepository;
import com.study.blog.account.domain.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleDataRunner implements CommandLineRunner, Ordered {
    private final RoleRepository roleRepository;

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("create default rules");
        if (roleRepository.count() == 0) {
            RoleType.toSet().forEach(roleType -> {
                roleRepository.save(new Role(roleType.name()));
            });
        }
    }
}
