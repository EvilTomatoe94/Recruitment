package com.recruitment.exercise.api.usertransactions;

import com.recruitment.exercise.domain.usertransaction.UserTransaction;

public class UserTransactionResponseDtoFactory {

    public static UserTransactionResponseDto create(UserTransaction userTransaction) {
        return UserTransactionResponseDto.builder()
                .id(userTransaction.getId())
                .login(userTransaction.getUser().getLogin())
                .amount(userTransaction.getAmount())
                .reward(userTransaction.getReward())
                .build();
    }
}
