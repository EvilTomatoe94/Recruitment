package com.recruitment.exercise.domain.usertransactions

import com.recruitment.exercise.domain.usertransaction.UserTransactionFactory
import spock.lang.Specification

import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFaker.*

class UserTransactionFactoryTest extends Specification {

    def 'should map UserTransactionEntity to UserTransaction'() {
        when:
        def transaction = UserTransactionFactory.create(USER_TRANSACTION_1_WITH_ID)

        then:
        verifyAll(transaction) {
            id == USER_TRANSACTION_1_WITH_ID.id
            reward == USER_TRANSACTION_1_WITH_ID.reward
            user.id == USER_TRANSACTION_1_WITH_ID.user.id
            amount == USER_TRANSACTION_1_WITH_ID.amount
        }
    }
}
