package com.recruitment.exercise.domain.users

import spock.lang.Specification

import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.*

class UserFactoryTest extends Specification {

    def 'should create user'() {
        when:
        User user = UserFactory.create(EXISTING_USER.toBuilder()
                .id(1L)
                .build()
        )

        then:
        verifyAll(user) {
            id == EXISTING_USER.id
            login == EXISTING_USER.login
        }
    }
}
