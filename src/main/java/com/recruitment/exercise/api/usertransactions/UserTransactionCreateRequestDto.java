package com.recruitment.exercise.api.usertransactions;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public class UserTransactionCreateRequestDto {

    @ApiModelProperty(required = true, example = "1.20")
    private BigDecimal amount;

    @ApiModelProperty(required = true, example = "kamila.w")
    private String login;
}
