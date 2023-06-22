package com.recruitment.exercise.domain.users;

import com.recruitment.exercise.infrastructure.users.UserEntity;

public class UserFactory {

    public static User create(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .login(user.getLogin())
                .build();
    }
}
