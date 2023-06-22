package com.recruitment.exercise.api.usertransactions

class UserTransactionUpdateRequestDtoFaker {

    static UserTransactionUpdateRequestDto UPDATE_REQUEST = UserTransactionUpdateRequestDto.of(new BigDecimal(120))

    static UserTransactionUpdateRequestDto UPDATE_REQUEST_WITH_NULL = UserTransactionUpdateRequestDto.of(null)

    static UserTransactionUpdateRequestDto UPDATE_REQUEST_AMOUNT_NOT_POSITIVE = UserTransactionUpdateRequestDto.of(new BigDecimal(-120))

    static UserTransactionUpdateRequestDto UPDATE_REQUEST_AMOUNT_PRECISION_INCORRECT = UserTransactionUpdateRequestDto.of(new BigDecimal(120.233))
}
