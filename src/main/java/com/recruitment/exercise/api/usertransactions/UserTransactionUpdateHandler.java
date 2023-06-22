package com.recruitment.exercise.api.usertransactions;

import com.recruitment.exercise.domain.usertransaction.UserTransaction;
import com.recruitment.exercise.domain.usertransaction.UserTransactionApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PACKAGE;

@Component
@RequiredArgsConstructor(access = PACKAGE)
public class UserTransactionUpdateHandler {

    private final UserTransactionApplicationService userTransactionApplicationService;

    public UserTransactionResponseDto handleUserTransactionUpdate(Long id, UserTransactionUpdateRequestDto requestDto) {
        UserTransaction transaction = userTransactionApplicationService.updateTransaction(id, requestDto.getAmount());
        return UserTransactionResponseDtoFactory.create(transaction);
    }
}
