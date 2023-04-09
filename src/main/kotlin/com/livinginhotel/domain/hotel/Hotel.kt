package com.livinginhotel.domain.hotel

import com.livinginhotel.common.Domain
import com.livinginhotel.domain.hotel.command.HotelSaveCommand
import java.time.LocalDateTime


@Domain
data class Hotel(

    val id: Long = 0,

    var name: String,

    val status: HotelStatus = HotelStatus.EXIST,

    var roomQuantity: Int,

    var version: Long? = null,

    val createDateTime: LocalDateTime? = null,

    val updateDateTime: LocalDateTime? = null,
) {
    fun reduceRoomQuantity() {
        this.roomQuantity -= 1
    }

    companion object {
        fun createHotel(hotelSaveCommand: HotelSaveCommand): Hotel {
            return Hotel(
                name = hotelSaveCommand.name,
                roomQuantity = hotelSaveCommand.roomQuantity
            )
        }
    }

}