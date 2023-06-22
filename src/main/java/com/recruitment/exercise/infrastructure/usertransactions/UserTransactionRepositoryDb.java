package com.recruitment.exercise.infrastructure.usertransactions;

import com.recruitment.exercise.domain.exceptions.ResourceNotFoundException;
import com.recruitment.exercise.domain.exceptions.ResourceNotFoundExceptionType;
import com.recruitment.exercise.domain.users.User;
import com.recruitment.exercise.domain.usertransaction.UserTransaction;
import com.recruitment.exercise.domain.usertransaction.UserTransactionFactory;
import com.recruitment.exercise.domain.usertransaction.UserTransactionRepository;
import com.recruitment.exercise.infrastructure.users.UserEntity;
import com.recruitment.exercise.infrastructure.users.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.recruitment.exercise.domain.exceptions.ResourceNotFoundExceptionType.*;
import static com.recruitment.exercise.infrastructure.usertransactions.UserTransactionEntityFactory.*;
import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
public class UserTransactionRepositoryDb implements UserTransactionRepository {

    private final UserTransactionJpaRepository userTransactionJpaRepository;

    // todo commanda tu dać
    @Override
    public UserTransaction createUserTransaction(BigDecimal amount, User user, int reward) {
        UserTransactionEntity userTransaction = userTransactionJpaRepository.save(create(
                        amount,
                        UserEntityFactory.create(user),
                        reward));

        return UserTransactionFactory.create(userTransaction);
    }

    @Override
    public UserTransaction updateUserTransaction(Long id, BigDecimal amount, int reward) {
        return userTransactionJpaRepository.findById(id)
                .map(transaction -> UserTransactionEntityFactory.update(transaction, amount, reward))
                .map(userTransactionJpaRepository::save)
                .map(UserTransactionFactory::create)
                .orElseThrow(() -> new ResourceNotFoundException(USER_TRANSACTION_NOT_FOUND));
    }

    @Override
    public List<UserTransaction> findAllUserTransactions(User user) {
        return userTransactionJpaRepository.findAllByUser(UserEntityFactory.create(user))
                .stream()
                .map(UserTransactionFactory::create)
                .collect(Collectors.toList());
    }
}
