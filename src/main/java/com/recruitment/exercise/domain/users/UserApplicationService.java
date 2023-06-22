package com.recruitment.exercise.domain.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
@Transactional
public class UserApplicationService {

    private final UserRepository userRepository;

    public User getUser(String login) {
        return userRepository.getOrCreateUser(login);
    }

    public User getUserOrThrowException(String login) {
        return userRepository.getUser(login);
    }
}
