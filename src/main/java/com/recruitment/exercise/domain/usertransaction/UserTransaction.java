package com.recruitment.exercise.domain.usertransaction;

import com.recruitment.exercise.domain.users.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Getter
public class UserTransaction {

    @NonNull
    private Long id;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private User user;

    @NonNull
    private int reward;
}
