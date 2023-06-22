package com.recruitment.exercise.api.usertransactions;

import com.recruitment.exercise.domain.users.User;
import com.recruitment.exercise.domain.users.UserApplicationService;
import com.recruitment.exercise.domain.usertransaction.UserTransactionApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PACKAGE;

@Component
@RequiredArgsConstructor(access = PACKAGE)
public class UserTransactionQueryHandler {

    private final UserTransactionApplicationService userTransactionApplicationService;
    private final UserApplicationService userApplicationService;

    public RewardResponseDto handleCalculateRewardForUser(String login) {
        User user = userApplicationService.getUserOrThrowException(login);
        return RewardResponseDto.of(userTransactionApplicationService.calculateRewards(user));
    }
}
