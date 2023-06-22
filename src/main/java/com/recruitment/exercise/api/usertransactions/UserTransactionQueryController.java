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
public class UserTransactionQueryController {

    private final UserTransactionQueryHandler userTransactionQueryHandler;

    @GetMapping(value = "/{login}/reward", produces = {"application/app-v1.0+json"})
    @ApiOperation(value = "Calculate reward for user")
    @ApiResponses({
            @ApiResponse(code = OK, message = "Success")
    })
    public RewardResponseDto calculateRewardForUser(@PathVariable String login) {
        return userTransactionQueryHandler.handleCalculateRewardForUser(login);
    }
}
