package com.recruitment.exercise.api.usertransactions

import com.fasterxml.jackson.core.type.TypeReference
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
}
