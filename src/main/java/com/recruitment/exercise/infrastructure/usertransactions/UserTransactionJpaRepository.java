package com.recruitment.exercise.infrastructure.usertransactions;

import com.recruitment.exercise.infrastructure.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTransactionJpaRepository  extends JpaRepository<UserTransactionEntity, Long> {

    List<UserTransactionEntity> findAllByUser(UserEntity user);
}
