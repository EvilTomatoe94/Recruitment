package com.recruitment.exercise.domain.usertransaction;

import com.recruitment.exercise.domain.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
@Transactional
public class UserTransactionApplicationService {

    private final UserTransactionRepository userTransactionRepository;

    public UserTransaction createTransaction(BigDecimal amount,
                                             User user) {
        return userTransactionRepository.createUserTransaction(amount, user, calculateReward(amount));
    }

    public UserTransaction updateTransaction(Long id,
                                             BigDecimal amount) {
        return userTransactionRepository.updateUserTransaction(id, amount, calculateReward(amount));
    }

    public int calculateRewards(User user) {
        return userTransactionRepository.findAllUserTransactions(user).stream()
                .map(UserTransaction::getReward)
                .reduce(0, Integer::sum);
    }

    public int calculateReward(BigDecimal amount) {
        int result = amount.intValue() > 50 ? amount.intValue() - 50 : 0;
        if (amount.intValue() > 100) {
            result = result + (amount.intValue() - 100) * 2;
        }

        return result;
    }
}
