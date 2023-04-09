package com.livinginhotel.adapter.inbound.web.booking.dto.request

import jakarta.validation.constraints.NotNull

data class BookingRequest(
    @field:NotNull(message = "호텔 Id는 필수입니다.")
    val hotelId: Long
)
