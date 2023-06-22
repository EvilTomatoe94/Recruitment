package com.recruitment.exercise.api.usertransactions

import com.recruitment.exercise.ApplicationBaseFT
import com.recruitment.exercise.infrastructure.users.UserEntity
import com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntity

import static com.recruitment.exercise.api.usertransactions.UserTransactionUpdateRequestDtoFaker.*
import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.EXISTING_USER
import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFaker.getUSER_TRANSACTION_1
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

class UserTransactionUpdateControllerFT extends ApplicationBaseFT {

    def 'should update UserTransaction and return response with status 200'() {
        given:
        UserEntity userEntity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )
        UserTransactionEntity transactionEntity = transactionTemplate.execute(status ->
                userTransactionJpaRepository.save(USER_TRANSACTION_1.toBuilder()
                        .user(userEntity)
                        .build())
        )

        when:
        def response = mockMvc.perform(put("/user-transactions/${transactionEntity.id}")
                .content(objectMapper.writeValueAsString(UPDATE_REQUEST))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        def result = objectMapper.readValue(response.contentAsString, UserTransactionResponseDto.class)
        response.status == OK.value()
        verifyAll(result) {
            id != null
            login == transactionEntity.user.login
            amount == UPDATE_REQUEST.amount
            reward == 110
        }
    }

    def 'should return status 464'() {
        when:
        def response = mockMvc.perform(put("/user-transactions/${Long.MAX_VALUE}")
                .content(objectMapper.writeValueAsString(UPDATE_REQUEST))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        response.status == 464
    }

    def 'should return status 460 when login null'() {
        when:
        def response = mockMvc.perform(put("/user-transactions/${Long.MAX_VALUE}")
                .content(objectMapper.writeValueAsString(UPDATE_REQUEST_WITH_NULL))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        response.status == 460
    }

    def 'should return status 460 when amount negative number'() {
        when:
        def response = mockMvc.perform(put("/user-transactions/${Long.MAX_VALUE}")
                .content(objectMapper.writeValueAsString(UPDATE_REQUEST_AMOUNT_NOT_POSITIVE))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        response.status == 460
    }

    def 'should return status 460 when amount scale more than 2'() {
        when:
        def response = mockMvc.perform(put("/user-transactions/${Long.MAX_VALUE}")
                .content(objectMapper.writeValueAsString(UPDATE_REQUEST_AMOUNT_PRECISION_INCORRECT))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        response.status == 460
    }
}
