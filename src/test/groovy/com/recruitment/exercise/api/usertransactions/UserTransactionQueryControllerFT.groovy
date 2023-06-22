package com.recruitment.exercise.api.usertransactions

import com.recruitment.exercise.ApplicationBaseFT
import com.recruitment.exercise.infrastructure.users.UserEntity
import com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntity

import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.EXISTING_USER
import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.NON_EXISTING_USER
import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFaker.USER_TRANSACTION
import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFaker.USER_TRANSACTION_1
import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class UserTransactionQueryControllerFT extends ApplicationBaseFT {

    def 'should return all user transactions with status 200'() {
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
        def response = mockMvc.perform(get("/user-transactions/${userEntity.login}/reward"))
                .andReturn().response

        then:
        def result = objectMapper.readValue(response.contentAsString, RewardResponseDto.class)
        response.status == OK.value()
        result.reward == transactionEntity1.reward + transactionEntity2.reward
    }

    def 'should return  status 464'() {
        when:
        def response = mockMvc.perform(get("/user-transactions/${NON_EXISTING_USER.login}/reward"))
                .andReturn().response

        then:
        response.status == 464
    }
}
