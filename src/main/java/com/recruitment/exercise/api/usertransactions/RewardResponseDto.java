package com.recruitment.exercise.api.usertransactions;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class RewardResponseDto {

    @ApiModelProperty(required = true, example = "20")
    private Integer reward;
}
