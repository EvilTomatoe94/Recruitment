package com.recruitment.exercise.api.usertransactions

import com.recruitment.exercise.ApplicationBaseFT

import static com.recruitment.exercise.api.usertransactions.UserTransactionCreateRequestDtoFaker.*
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


class UserTransactionCreateControllerFT extends ApplicationBaseFT {

    def 'should create transaction and return response with status 200'() {
        when:
        def response = mockMvc.perform(post("/user-transactions")
                .content(objectMapper.writeValueAsString(CREATE_REQUEST))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        def result = objectMapper.readValue(response.contentAsString, UserTransactionResponseDto.class)
        response.status == OK.value()
        verifyAll(result) {
            id != null
            login == CREATE_REQUEST.login
            amount == CREATE_REQUEST.amount
            reward == 110
        }
    }

    def 'should return status 460 when login empty'() {
        when:
        def response = mockMvc.perform(post("/user-transactions")
                .content(objectMapper.writeValueAsString(CREATE_REQUEST_EMPTY_FIELD))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        response.status == 460
    }

    def 'should return status 460 when amount equals 0'() {
        when:
        def response = mockMvc.perform(post("/user-transactions")
                .content(objectMapper.writeValueAsString(CREATE_REQUEST_AMOUNT_NOT_POSITIVE))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        response.status == 460
    }

    def 'should return status 460 when amount scale more than 2'() {
        when:
        def response = mockMvc.perform(post("/user-transactions")
                .content(objectMapper.writeValueAsString(CREATE_REQUEST_AMOUNT_PRECISION_INCORRECT))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        then:
        response.status == 460
    }
}
