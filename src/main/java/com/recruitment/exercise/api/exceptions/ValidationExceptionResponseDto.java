package com.recruitment.exercise.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationExceptionResponseDto {

    private int errorCode;

    private String parameter;
}
