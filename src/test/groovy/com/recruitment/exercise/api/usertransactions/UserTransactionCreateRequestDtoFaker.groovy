package com.recruitment.exercise.api.usertransactions

class UserTransactionCreateRequestDtoFaker {

    static UserTransactionCreateRequestDto CREATE_REQUEST = UserTransactionCreateRequestDto.of(new BigDecimal(120), "kamila.w")

    static UserTransactionCreateRequestDto CREATE_REQUEST_EMPTY_FIELD = UserTransactionCreateRequestDto.of(new BigDecimal(120), "")

    static UserTransactionCreateRequestDto CREATE_REQUEST_AMOUNT_NOT_POSITIVE = UserTransactionCreateRequestDto.of(new BigDecimal(0), "kamila.w")

    static UserTransactionCreateRequestDto CREATE_REQUEST_AMOUNT_PRECISION_INCORRECT = UserTransactionCreateRequestDto.of(new BigDecimal(1.222), "kamila.w")
}
