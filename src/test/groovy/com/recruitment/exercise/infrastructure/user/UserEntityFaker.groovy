package com.recruitment.exercise.infrastructure.user

import com.recruitment.exercise.infrastructure.users.UserEntity

class UserEntityFaker {

    static UserEntity EXISTING_USER = UserEntity.builder()
            .login("kamila.w")
            .build()

    static UserEntity NON_EXISTING_USER = UserEntity.builder()
            .login("kamila.w2")
            .build()
}
