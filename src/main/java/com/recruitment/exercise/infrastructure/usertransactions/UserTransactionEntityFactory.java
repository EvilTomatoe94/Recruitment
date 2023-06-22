package com.recruitment.exercise.infrastructure.usertransactions;

import com.recruitment.exercise.infrastructure.users.UserEntity;

import java.math.BigDecimal;

public class UserTransactionEntityFactory {

    public static UserTransactionEntity create(BigDecimal amount, UserEntity user, int reward) {
        return UserTransactionEntity.builder()
                .user(user)
                .amount(amount)
                .reward(reward)
                .build();
    }

    public static UserTransactionEntity update(UserTransactionEntity entity, BigDecimal amount, int reward) {
        return entity.toBuilder()
                .amount(amount)
                .reward(reward)
                .build();
    }
}
