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
public class UserTransactionUpdateController {

    private final UserTransactionUpdateHandler userTransactionUpdateHandler;

    // Dać zwrotkę
    @PutMapping(value = "/{id}", produces = {"application/app-v1.0+json"})
    @ApiOperation(value = "Update transaction")
    @ApiResponses({
            @ApiResponse(code = OK, message = "Success")
    })
    public UserTransactionResponseDto updateUserTransaction(@PathVariable Long id, @RequestBody UserTransactionUpdateRequestDto requestDto) {
        return userTransactionUpdateHandler.handleUserTransactionUpdate(id, requestDto);
    }
}
