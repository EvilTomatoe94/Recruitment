package com.recruitment.exercise.domain.usertransactions

import com.recruitment.exercise.ApplicationBaseIT
import com.recruitment.exercise.domain.exceptions.ResourceNotFoundException
import com.recruitment.exercise.domain.users.User
import com.recruitment.exercise.infrastructure.users.UserEntity
import com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntity

import static com.recruitment.exercise.domain.users.UserFaker.*
import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.EXISTING_USER
import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFaker.*

class UserTransactionApplicationServiceIT extends ApplicationBaseIT {

    def 'should create transaction'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )

        when:
        def transaction = userTransactionApplicationService.createTransaction(
                new BigDecimal(150),
                User.builder()
                        .login(userEntity.login)
                        .id(userEntity.id)
                        .build())

        then:
        Optional<UserTransactionEntity> savedTransaction = userTransactionJpaRepository.findById(transaction.id)
        verifyAll(savedTransaction.get()) {
            amount == new BigDecimal(150)
            user.id == userEntity.id
            reward == 200
        }
    }

    def 'should create transaction with reward 0'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )

        when:
        def transaction = userTransactionApplicationService.createTransaction(
                new BigDecimal(40),
                USER.toBuilder()
                        .id(userEntity.id)
                        .build())

        then:
        Optional<UserTransactionEntity> savedTransaction = userTransactionJpaRepository.findById(transaction.id)
        savedTransaction.get().reward == 0
    }

    def 'should update transaction'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )
        UserTransactionEntity transactionEntity = transactionTemplate.execute(status ->
                userTransactionJpaRepository.save(USER_TRANSACTION.toBuilder()
                        .user(userEntity)
                        .build())
        )

        when:
        userTransactionApplicationService.updateTransaction(transactionEntity.id, new BigDecimal(200))


        then:
        Optional<UserTransactionEntity> updatedTransaction = userTransactionJpaRepository.findById(transactionEntity.id)
        verifyAll(updatedTransaction.get()) {
            amount == new BigDecimal(200)
            reward == 350
        }
    }

    def 'should throw ResourceNotFoundException'() {
        when:
        userTransactionApplicationService.updateTransaction(Integer.MAX_VALUE, new BigDecimal(200))


        then:
        thrown(ResourceNotFoundException)
    }

    def 'should correctly calculate rewards'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )
        UserTransactionEntity transactionEntity1 = transactionTemplate.execute(status ->
                userTransactionJpaRepository.save(USER_TRANSACTION.toBuilder()
                        .user(userEntity)
                        .build())
        )
        UserTransactionEntity transactionEntity2 = transactionTemplate.execute(status ->
                userTransactionJpaRepository.save(USER_TRANSACTION_1.toBuilder()
                        .user(userEntity)
                        .build())
        )

        when:
        def rewards = userTransactionApplicationService.calculateRewards(USER.toBuilder().id(userEntity.id).build())


        then:
        rewards == transactionEntity1.reward + transactionEntity2.reward
    }

    def 'should correctly calculate rewards for no transactions present for user'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )

        when:
        def rewards = userTransactionApplicationService.calculateRewards(USER.toBuilder().id(userEntity.id).build())


        then:
        rewards == 0
    }
}
