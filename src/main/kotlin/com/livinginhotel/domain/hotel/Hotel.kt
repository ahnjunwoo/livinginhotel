package com.livinginhotel.domain.hotel

import com.livinginhotel.common.Domain
import com.livinginhotel.domain.hotel.command.HotelSaveCommand
import java.time.LocalDateTime


@Domain
data class Hotel(

    val id: Long = 0,

    var name: String,

    var status: HotelStatus = HotelStatus.EXIST,

    var roomQuantity: Int,

    var version: Long? = null,

    val createDateTime: LocalDateTime? = null,

    val updateDateTime: LocalDateTime? = null,
) {
    fun reduceRoomQuantity() {
        this.roomQuantity -= 1
        if(this.roomQuantity == 0) {
            this.status = HotelStatus.NOT_EXIST
        }
    }

    fun isNotExist(): Boolean {
        return this.status == HotelStatus.NOT_EXIST || this.roomQuantity == 0
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