package com.recruitment.exercise.api.exceptions;

import com.recruitment.exercise.domain.exceptions.ResourceNotFoundExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundExceptionResponseDto {

    private ResourceNotFoundExceptionType type;

    private int errorCode;
}
