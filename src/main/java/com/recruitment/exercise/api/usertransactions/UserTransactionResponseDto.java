package com.recruitment.exercise.api.usertransactions;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class UserTransactionResponseDto {

    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @ApiModelProperty(required = true, example = "kamila.w")
    private String login;

    @ApiModelProperty(required = true, example = "1.20")
    private BigDecimal amount;

    @ApiModelProperty(required = true, example = "20")
    private Integer reward;
}
