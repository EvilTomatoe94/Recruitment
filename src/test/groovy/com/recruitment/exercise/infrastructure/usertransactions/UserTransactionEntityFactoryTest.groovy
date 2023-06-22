package com.recruitment.exercise.infrastructure.usertransactions

import com.recruitment.exercise.infrastructure.users.UserEntity
import spock.lang.Specification

import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.*
import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFaker.*

class UserTransactionEntityFactoryTest extends Specification {

    def 'should create UserTransactionEntity'() {
        given:
        BigDecimal amount = new BigDecimal(120);
        int reward = 90
        UserEntity user = EXISTING_USER_WITH_ID

        when:
        UserTransactionEntity entity = UserTransactionEntityFactory.create(amount, user, reward)

        then:
        verifyAll(entity) {
            entity.amount == amount
            entity.user.id == user.id
            entity.reward == reward
        }
    }

    def 'should update UserTransactionEntity'() {
        given:
        BigDecimal amount = new BigDecimal(120);
        int reward = 90

        when:
        UserTransactionEntity entity = UserTransactionEntityFactory.update(USER_TRANSACTION_1_WITH_ID, amount, reward)

        then:
        verifyAll(entity) {
            entity.amount == amount
            entity.reward == reward
            entity.id == USER_TRANSACTION_1_WITH_ID.id
            entity.user.id == USER_TRANSACTION_1_WITH_ID.user.id
        }
    }
}
