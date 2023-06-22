package com.recruitment.exercise.api.usertransactions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.recruitment.exercise.domain.exceptions.RC.OK;
import static com.recruitment.exercise.domain.exceptions.RC.RESOURCE_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("user-transactions")
@Api(tags = "User transactions")
public class UserTransactionUpdateController {

    private final UserTransactionUpdateHandler userTransactionUpdateHandler;

    @PutMapping("/{id}")
    @ApiOperation(value = "Update transaction")
    @ApiResponses({
            @ApiResponse(code = OK, message = "Success"),
            @ApiResponse(code = RESOURCE_NOT_FOUND, message = "When transaction does not exist (USER_TRANSACTION_NOT_FOUND)")
    })
    public UserTransactionResponseDto updateUserTransaction(@PathVariable Long id, @RequestBody UserTransactionUpdateRequestDto requestDto) {
        return userTransactionUpdateHandler.handleUserTransactionUpdate(id, requestDto);
    }
}
