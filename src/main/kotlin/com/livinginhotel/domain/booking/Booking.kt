package com.livinginhotel.domain.booking

import com.livinginhotel.common.Domain
import java.time.LocalDateTime


@Domain
data class Booking(

    val id: Long = 0,

    val hotelId: Long,

    var status: BookingStatus = BookingStatus.REQUEST,

    val createDateTime: LocalDateTime? = null,

    val updateDateTime: LocalDateTime? = null
) {
    fun confirm(status: BookingStatus) {
        this.status = status
    }

    companion object {
        fun booking(hotelId: Long): Booking {
            return Booking(hotelId = hotelId)
        }
    }

}