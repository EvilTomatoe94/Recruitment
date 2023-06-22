package com.recruitment.exercise.infrastructure.users;


import com.recruitment.exercise.domain.users.User;

public class UserEntityFactory {

    public static UserEntity create(String login) {
        return UserEntity.builder()
                .login(login)
                .build();
    }

    public static UserEntity create(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .login(user.getLogin())
                .build();
    }
}
