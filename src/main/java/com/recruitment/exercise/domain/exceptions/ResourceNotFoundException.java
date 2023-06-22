package com.recruitment.exercise.domain.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final ResourceNotFoundExceptionType msgKey;
    private final int errorCode;

    public ResourceNotFoundException(ResourceNotFoundExceptionType msgKey) {
        this.msgKey = msgKey;
        this.errorCode = RC.RESOURCE_NOT_FOUND;
    }
}
