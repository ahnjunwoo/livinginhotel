package com.livinginhotel.adapter.inbound.web.booking.dto.request

import com.livinginhotel.domain.booking.BookingStatus

data class BookingConfirmRequest(

    val status: BookingStatus
)
