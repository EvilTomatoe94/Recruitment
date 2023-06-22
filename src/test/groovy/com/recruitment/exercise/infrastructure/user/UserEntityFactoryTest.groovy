package com.recruitment.exercise.infrastructure.user

import com.recruitment.exercise.infrastructure.users.UserEntity
import com.recruitment.exercise.infrastructure.users.UserEntityFactory
import spock.lang.Specification

import static com.recruitment.exercise.domain.users.UserFaker.*
import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.*

class UserEntityFactoryTest extends Specification {

    def 'should create user entity from login'() {
        when:
        UserEntity userEntity = UserEntityFactory.create(NON_EXISTING_USER.login)

        then:
        userEntity.login == NON_EXISTING_USER.login
    }

    def 'should update user entity from User'() {
        when:
        UserEntity userEntity = UserEntityFactory.create(USER)

        then:
        verifyAll(userEntity) {
            id == USER.id
            login == USER.login
        }
    }
}
