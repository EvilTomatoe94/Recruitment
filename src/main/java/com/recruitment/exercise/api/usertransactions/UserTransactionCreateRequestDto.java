package com.recruitment.exercise.api.usertransactions;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public class UserTransactionCreateRequestDto {

    @ApiModelProperty(required = true, example = "1.20")
    @NotEmpty
    private BigDecimal amount;

    @ApiModelProperty(required = true, example = "kamila.w")
    @NotEmpty
    private String login;
}
