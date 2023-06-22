package com.recruitment.exercise.api.usertransactions;

import com.recruitment.exercise.domain.users.User;
import com.recruitment.exercise.domain.users.UserApplicationService;
import com.recruitment.exercise.domain.usertransaction.UserTransactionApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PACKAGE;

@Component
@RequiredArgsConstructor(access = PACKAGE)
public class UserTransactionCreateHandler {

    private final UserTransactionApplicationService userTransactionApplicationService;
    private final UserApplicationService userApplicationService;

    public UserTransactionResponseDto handleCreateUserTransaction(UserTransactionCreateRequestDto requestDto) {
        User user = userApplicationService.getUser(requestDto.getLogin());
        return UserTransactionResponseDtoFactory.create(userTransactionApplicationService.createTransaction(requestDto.getAmount(), user));
    }
}
