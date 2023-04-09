package com.livinginhotel.domain.hotel.command

import com.livinginhotel.common.NoArg


@NoArg
data class HotelSaveCommand(
    val name: String,

    val roomQuantity: Int,
)
