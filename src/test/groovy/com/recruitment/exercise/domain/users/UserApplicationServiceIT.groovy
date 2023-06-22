package com.recruitment.exercise.domain.users

import com.recruitment.exercise.ApplicationBaseIT
import com.recruitment.exercise.domain.exceptions.ResourceNotFoundException

import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.NON_EXISTING_USER
import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.EXISTING_USER

class UserApplicationServiceIT extends ApplicationBaseIT {

    // getUser gdy jest user i gdy nie ma usera
    // getUserOrThrowException gdy jest i gdy nie ma

    def 'should get user when user already exists'() {
        given:
        transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )

        when:
        def result = userApplicationService.getUser(EXISTING_USER.login)

        then:
        verifyAll(result) {
            id != null
            login == EXISTING_USER.login
        }
    }

    def 'should create new user'() {
        when:
        def result = userApplicationService.getUser(NON_EXISTING_USER.login)

        then:
        verifyAll(result) {
            id != null
            login == NON_EXISTING_USER.login
        }
    }

    def 'should get user'() {
        given:
        transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )

        when:
        def result = userApplicationService.getUserOrThrowException(EXISTING_USER.login)

        then:
        verifyAll(result) {
            id != null
            login == EXISTING_USER.login
        }
    }

    def 'should throw ResourceNotFoundException'() {
        when:
        def result = userApplicationService.getUserOrThrowException(NON_EXISTING_USER.login)

        then:
        thrown(ResourceNotFoundException)
    }

}
