package com.livinginhotel.exception


class ExceptionResponse(
        var code: Int,
        var message: String
) {
    constructor(apiCode: ApiCode) : this(apiCode.code.value(), apiCode.message) {
        this.code = apiCode.code.value()
        this.message = apiCode.message
    }
}