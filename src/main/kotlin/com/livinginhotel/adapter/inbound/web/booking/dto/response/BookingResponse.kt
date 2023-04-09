package com.livinginhotel.adapter.inbound.web.booking.dto.response

import com.livinginhotel.common.NoArg
import com.livinginhotel.domain.booking.BookingStatus
import java.time.LocalDateTime

@NoArg
data class BookingResponse(
    val id: Long,

    val hotelId: Long,

    val status: BookingStatus,

    val createDateTime: LocalDateTime?= null,

    val updateDateTime: LocalDateTime?= null
)