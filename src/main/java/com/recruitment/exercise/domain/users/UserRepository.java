package com.recruitment.exercise.domain.users;

public interface UserRepository {

    User getOrCreateUser(String login);

    User getUser(String login);
}
