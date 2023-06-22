package com.recruitment.exercise.api.usertransactions

import com.fasterxml.jackson.core.type.TypeReference
import com.recruitment.exercise.ApplicationBaseFT

import static com.recruitment.exercise.api.usertransactions.UserTransactionCreateRequestDtoFaker.*
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


class UserTransactionCreateControllerFT extends ApplicationBaseFT {

    def 'should create transaction and return response'() {
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
            reward == 90
        }
    }

//    def 'should return 464'() {
//        given:
//        userRepositoryGit.getUserGitData(USER_GIT_DETAILS.login) >> { throw new ResourceNotFoundException(USER_NOT_FOUND) }
//
//        when:
//        def response = mockMvc.perform(get("/users/${USER_DETAILS.userGitDetails.login}"))
//                .andReturn().response
//
//        then:
//        response.status == 464
//    }
}
