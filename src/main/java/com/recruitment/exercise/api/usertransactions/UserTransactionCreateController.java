package com.recruitment.exercise.api.usertransactions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.recruitment.exercise.domain.exceptions.RC.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("user-transactions")
@Api(tags = "User transactions")
public class UserTransactionCreateController {
    private final UserTransactionCreateHandler userTransactionCreateHandler;
    @PostMapping
    @ApiOperation(value = "Create transaction for a user")
    @ApiResponses({
            @ApiResponse(code = OK, message = "Success")
    })
    public UserTransactionResponseDto createUserTransaction(@RequestBody UserTransactionCreateRequestDto requestDto) {
        return userTransactionCreateHandler.handleCreateUserTransaction(requestDto);
    }
}
