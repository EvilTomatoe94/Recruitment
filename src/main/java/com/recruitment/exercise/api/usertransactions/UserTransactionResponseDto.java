package com.recruitment.exercise.api.usertransactions;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@NoArgsConstructor
public class UserTransactionResponseDto {
    private Long id;
    private String login;
    private BigDecimal amount;
    private Integer reward;
}
