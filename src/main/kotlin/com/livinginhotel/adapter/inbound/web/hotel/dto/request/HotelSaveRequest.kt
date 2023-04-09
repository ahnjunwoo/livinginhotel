package com.livinginhotel.adapter.inbound.web.hotel.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class HotelSaveRequest(
    @field:NotEmpty(message = "호텔 이름을 필수입니다.")
    val name: String,

    @field:NotNull(message = "호텔 객실 수량은 필수입니다.")
    @field:Min(value = 1, message = "호텔 객실 수량은 최소 1입니다.")
    val roomQuantity: Int,
)
