package com.recruitment.exercise.infrastructure.users;

import com.recruitment.exercise.domain.exceptions.ResourceNotFoundException;
import com.recruitment.exercise.domain.users.User;
import com.recruitment.exercise.domain.users.UserFactory;
import com.recruitment.exercise.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.recruitment.exercise.domain.exceptions.ResourceNotFoundExceptionType.USER_NOT_FOUND;
import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
public class UserRepositoryDb implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User getOrCreateUser(String login) {
        UserEntity user =  userJpaRepository.findByLogin(login)
                .orElseGet(() -> userJpaRepository.save(UserEntityFactory.create(login)));
        return UserFactory.create(user);
    }

    @Override
    public User getUser(String login) {
        return userJpaRepository.findByLogin(login)
                .map(UserFactory::create)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }
}
