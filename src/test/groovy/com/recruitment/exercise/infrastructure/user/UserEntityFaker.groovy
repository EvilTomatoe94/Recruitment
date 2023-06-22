package com.recruitment.exercise.infrastructure.user

import com.recruitment.exercise.infrastructure.users.UserEntity

class UserEntityFaker {

    static UserEntity EXISTING_USER = UserEntity.builder()
            .login("kamila.w")
            .build()

    static UserEntity NON_EXISTING_USER = UserEntity.builder()
            .login("kamila.w2")
            .build()

    static UserEntity EXISTING_USER_WITH_ID = UserEntity.builder()
            .id(2L)
            .login("kamila.w")
            .build()
}
