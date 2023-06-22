package com.recruitment.exercise.domain.usertransaction;

import com.recruitment.exercise.domain.users.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
@Transactional
@Slf4j
public class UserTransactionApplicationService {

    private final UserTransactionRepository userTransactionRepository;

    public UserTransaction createTransaction(BigDecimal amount,
                                             User user) {
        UserTransaction userTransaction = userTransactionRepository.createUserTransaction(amount, user, calculateReward(amount));
        log.info("Transaction for user {} created", user.getLogin());
        return userTransaction;
    }

    public UserTransaction updateTransaction(Long id,
                                             BigDecimal amount) {
        UserTransaction userTransaction = userTransactionRepository.updateUserTransaction(id, amount, calculateReward(amount));
        log.info("Transaction with id = {} updated", userTransaction.getId());
        return userTransaction;
    }

    public int calculateRewards(User user) {
        return userTransactionRepository.findAllUserTransactions(user).stream()
                .map(UserTransaction::getReward)
                .reduce(0, Integer::sum);
    }

    private int calculateReward(BigDecimal amount) {
        int result = amount.intValue() > 50 ? amount.intValue() - 50 : 0;
        if (amount.intValue() > 100) {
            result = result + (amount.intValue() - 100) * 2;
        }

        return result;
    }
}
