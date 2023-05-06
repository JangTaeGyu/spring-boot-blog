package com.study.blog.account.domain;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.study.blog.account.domain.QRole.role;
import static com.study.blog.account.domain.QUser.user;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JPAQueryFactory query;

    private ConstructorExpression<UserDto> selectFields() {
        return Projections.constructor(UserDto.class,
                user.code,
                user.email,
                user.name,
                user.imagePublicUrl,
                user.latestAccessedAt,
                user.createdAt,
                user.updatedAt,
                role.name);
    }

    @Override
    public void updateLatestAccessByEmail(String email) {
        query.update(user)
                .where(user.email.eq(email))
                .set(user.latestAccessedAt, LocalDateTime.now())
                .execute();
    }

    @Override
    public Optional<UserDto> findUserDetailsByCode(String code) {
        return Optional.ofNullable(
            query.select(selectFields())
                    .from(user)
                    .innerJoin(user.roles, role)
                    .where(
                            user.code.eq(code),
                            user.deletedAt.isNull())
                    .fetchFirst()
        );
    }
}
