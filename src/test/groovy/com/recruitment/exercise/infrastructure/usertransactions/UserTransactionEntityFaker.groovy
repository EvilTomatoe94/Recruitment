package com.recruitment.exercise.infrastructure.usertransactions

import com.recruitment.exercise.infrastructure.user.UserEntityFaker

import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.*

class UserTransactionEntityFaker {

    static UserTransactionEntity USER_TRANSACTION = UserTransactionEntity.builder()
            .amount(new BigDecimal(120))
            .reward(90)
            .build()

    static UserTransactionEntity USER_TRANSACTION_1 = UserTransactionEntity.builder()
            .amount(new BigDecimal(200))
            .reward(350)
            .build()

    static UserTransactionEntity USER_TRANSACTION_1_WITH_ID = UserTransactionEntity.builder()
            .id(1L)
            .user(EXISTING_USER_WITH_ID)
            .amount(new BigDecimal(200))
            .reward(350)
            .build()
}
