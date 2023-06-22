package com.recruitment.exercise.api.exceptions;

import com.recruitment.exercise.api.exceptions.ResourceNotFoundExceptionResponseDto;
import com.recruitment.exercise.domain.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Order(HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestControllerErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResourceNotFoundExceptionResponseDto onResourceNotFoundException(HttpServletRequest request,
                                                                            HttpServletResponse response,
                                                                            ResourceNotFoundException exception) {
        response.setStatus(exception.getErrorCode());
        ResourceNotFoundExceptionResponseDto dto = new ResourceNotFoundExceptionResponseDto(exception.getMsgKey(), exception.getErrorCode());
        request.setAttribute(ResourceNotFoundExceptionResponseDto.class.getName(), dto);
        log.error("ResourceNotFoundException of type {}", exception.getMsgKey().name());
        return dto;
    }
}
