package com.recruitment.exercise.infrastructure.usertransactions

import com.recruitment.exercise.ApplicationBaseIT
import com.recruitment.exercise.domain.exceptions.ResourceNotFoundException
import com.recruitment.exercise.domain.users.User
import com.recruitment.exercise.domain.usertransaction.UserTransaction
import com.recruitment.exercise.infrastructure.users.UserEntity

import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.EXISTING_USER
import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.NON_EXISTING_USER
import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFaker.*

class UserTransactionRepositoryDbIT extends ApplicationBaseIT {

    def 'should create UserTransaction'() {
        given:
        BigDecimal amountToBeSaved = new BigDecimal(120)
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )
        int rewardToBeSaved = 90

        when:
        def userTransaction = userTransactionRepositoryDb.createUserTransaction(
                amountToBeSaved,
                User.builder()
                        .id(userEntity.id)
                        .login(userEntity.login)
                        .build(),
                rewardToBeSaved)

        then:
        def userTransactionEntity = userTransactionJpaRepository.findById(userTransaction.id)
        verifyAll(userTransactionEntity.get()) {
            amount == amountToBeSaved
            reward == rewardToBeSaved
            user.id == userEntity.id
        }
    }

    def 'should update UserTransaction'() {
        given:
        BigDecimal amountToBeUpdated = new BigDecimal(120)
        int rewardToBeUpdated = 90
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )
        UserTransactionEntity transactionEntity = transactionTemplate.execute(status ->
                userTransactionJpaRepository.save(USER_TRANSACTION_1.toBuilder()
                        .user(userEntity)
                        .build())
        )


        when:
        userTransactionRepositoryDb.updateUserTransaction(transactionEntity.id, amountToBeUpdated, rewardToBeUpdated)

        then:
        def userTransactionEntity = userTransactionJpaRepository.findById(transactionEntity.id)
        verifyAll(userTransactionEntity.get()) {
            amount == amountToBeUpdated
            reward == rewardToBeUpdated
            user.id == userEntity.id
        }
    }

    def 'should throw ResourceNotFoundException'() {
        given:
        BigDecimal amountToBeUpdated = new BigDecimal(120)
        int rewardToBeUpdated = 90

        when:
        userTransactionRepositoryDb.updateUserTransaction(Long.MAX_VALUE, amountToBeUpdated, rewardToBeUpdated)

        then:
        thrown(ResourceNotFoundException)
    }

    def 'should return all user transactions'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )
        UserEntity anotherUser = transactionTemplate.execute(status ->
                userJpaRepository.save(NON_EXISTING_USER)
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
        transactionTemplate.execute(status ->
                userTransactionJpaRepository.save(USER_TRANSACTION_1.toBuilder()
                        .user(anotherUser)
                        .build())
        )

        when:
        List<UserTransaction> transactions = userTransactionRepositoryDb.findAllUserTransactions(User.builder()
                .id(userEntity.id)
                .login(userEntity.login)
                .build())

        then:
        verifyAll(transactions) {
            size() == 2
            get(0).id == transactionEntity1.id
            get(1).id == transactionEntity2.id
        }
    }

    def 'should return no user transactions'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )
        UserEntity anotherUser = transactionTemplate.execute(status ->
                userJpaRepository.save(NON_EXISTING_USER)
        )
        transactionTemplate.execute(status ->
                userTransactionJpaRepository.save(USER_TRANSACTION_1.toBuilder()
                        .user(anotherUser)
                        .build())
        )

        when:
        List<UserTransaction> transactions = userTransactionRepositoryDb.findAllUserTransactions(User.builder()
                .id(userEntity.id)
                .login(userEntity.login)
                .build())

        then:
        verifyAll(transactions) {
            size() == 0
        }
    }
}
