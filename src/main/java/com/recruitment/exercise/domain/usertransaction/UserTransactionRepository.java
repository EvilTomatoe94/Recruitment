package com.recruitment.exercise.domain.usertransaction;

import com.recruitment.exercise.domain.users.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserTransactionRepository {

    UserTransaction createUserTransaction(BigDecimal amount, User user, int reward);

    UserTransaction updateUserTransaction(Long id, BigDecimal amount, int reward);

    List<UserTransaction> findAllUserTransactions(User user);
}
