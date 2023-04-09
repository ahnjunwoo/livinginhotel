package com.livinginhotel.exception

class LivinginNonException(apiCode: ApiCode) : RuntimeException() {
    var code: Int
    override var message: String? = null

    init {
        this.code = apiCode.code.value()
        this.message = apiCode.message
    }
}
