package com.study.blog.account.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.study.blog.account.domain.QUser.user;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JPAQueryFactory query;

    @Override
    public void updateLatestAccessByEmail(String email) {
        query.update(user)
                .where(user.email.eq(email))
                .set(user.latestAccessedAt, LocalDateTime.now())
                .execute();
    }
}
