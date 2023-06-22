package com.recruitment.exercise.domain.users

class UserFaker {

    static User USER = User.builder()
            .id(1L)
            .login("kamila.w")
            .build()
}
