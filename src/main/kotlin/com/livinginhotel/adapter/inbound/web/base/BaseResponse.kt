package com.livinginhotel.adapter.inbound.web.base

import com.fasterxml.jackson.annotation.JsonInclude
import com.livinginhotel.exception.ApiCode

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseResponse<T>(
    var code: Int = ApiCode.SUCCESS.code.value(),
    var message: String = ApiCode.SUCCESS.message,
    var results: T? = null
) {
    constructor(apiCode: ApiCode, data: T) : this(apiCode.code.value(), apiCode.message, data) {
        this.code = apiCode.code.value()
        this.message = apiCode.message
        this.results = data
    }

    constructor(data: T) : this(results = data) {
        this.code = ApiCode.SUCCESS.code.value()
        this.message = ApiCode.SUCCESS.message
        this.results = data
    }

    constructor(apiCode: ApiCode) : this(
        code = apiCode.code.value(),
        message = apiCode.message
    )
}
