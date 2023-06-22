package com.recruitment.exercise.api.usertransactions;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserTransactionUpdateRequestDto {

    @ApiModelProperty(required = true, example = "1.20")
    @NotNull
    private BigDecimal amount;
}
