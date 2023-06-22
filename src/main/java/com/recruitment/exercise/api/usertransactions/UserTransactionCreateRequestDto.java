package com.recruitment.exercise.api.usertransactions;

import com.recruitment.exercise.domain.validation.ValidBigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public class UserTransactionCreateRequestDto {

    @ApiModelProperty(required = true, example = "1.20")
    @NotNull
    @ValidBigDecimal
    private BigDecimal amount;

    @ApiModelProperty(required = true, example = "kamila.w")
    @NotEmpty
    private String login;
}
