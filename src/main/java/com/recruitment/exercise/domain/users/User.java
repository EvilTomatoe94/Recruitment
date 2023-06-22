package com.recruitment.exercise.domain.users;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
public class User {

    @NonNull
    private Long id;

    @NonNull
    private String login;
}
