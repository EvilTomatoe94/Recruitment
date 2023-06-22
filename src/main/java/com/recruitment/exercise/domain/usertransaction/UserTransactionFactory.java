package com.recruitment.exercise.domain.usertransaction;

import com.recruitment.exercise.domain.users.UserFactory;
import com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntity;

public class UserTransactionFactory {

    public static UserTransaction create(UserTransactionEntity userTransaction) {
        return UserTransaction.builder()
                .id(userTransaction.getId())
                .amount(userTransaction.getAmount())
                .user(UserFactory.create(userTransaction.getUser()))
                .reward(userTransaction.getReward())
                .build();
    }
}
