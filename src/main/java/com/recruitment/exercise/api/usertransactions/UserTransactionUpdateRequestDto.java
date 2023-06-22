package com.recruitment.exercise.api.usertransactions;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserTransactionUpdateRequestDto {

    private BigDecimal amount;
}
