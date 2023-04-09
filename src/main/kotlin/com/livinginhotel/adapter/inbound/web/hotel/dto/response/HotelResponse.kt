package com.livinginhotel.adapter.inbound.web.hotel.dto.response

import com.livinginhotel.common.NoArg
import com.livinginhotel.domain.hotel.HotelStatus
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@NoArg
data class HotelResponse(
    val id: Long,

    val name: String,

    val roomQuantity: Int,

    val status: HotelStatus
)
