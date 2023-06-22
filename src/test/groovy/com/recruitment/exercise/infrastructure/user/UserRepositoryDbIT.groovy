package com.recruitment.exercise.infrastructure.user

import com.recruitment.exercise.ApplicationBaseIT
import com.recruitment.exercise.domain.exceptions.ResourceNotFoundException
import com.recruitment.exercise.domain.users.User
import com.recruitment.exercise.infrastructure.users.UserEntity

import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.NON_EXISTING_USER
import static com.recruitment.exercise.infrastructure.user.UserEntityFaker.getEXISTING_USER


class UserRepositoryDbIT extends ApplicationBaseIT {

    def 'should create new user'() {
        when:
        User user = userRepositoryDb.getOrCreateUser("kamila.w2")

        then:
        Optional<UserEntity> savedUser = userJpaRepository.findByLogin(user.login)

        verifyAll() {
            savedUser.isPresent()
            savedUser.get().login == user.login
            savedUser.get().id == user.id
        }
    }

    def 'should get already existing user'() {
        given:
        UserEntity entity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )

        when:
        User user = userRepositoryDb.getOrCreateUser(EXISTING_USER.login)

        then:
        verifyAll(user) {
            login == entity.login
            id == entity.id
        }
    }

    def 'should get already existing user with no exception'() {
        given:
        UserEntity entity = transactionTemplate.execute(status ->
                userJpaRepository.save(EXISTING_USER)
        )

        when:
        User user = userRepositoryDb.getUser(EXISTING_USER.login)

        then:
        verifyAll(user) {
            login == entity.login
            id == entity.id
        }
    }

    def 'should throw ResourceNotFoundException'() {
        when:
        User user = userRepositoryDb.getUser(NON_EXISTING_USER.login)

        then:
        thrown(ResourceNotFoundException)
    }
}
