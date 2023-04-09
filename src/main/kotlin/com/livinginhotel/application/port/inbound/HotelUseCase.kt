package com.livinginhotel.application.port.inbound

import com.livinginhotel.adapter.inbound.web.hotel.dto.request.HotelSaveRequest
import com.livinginhotel.adapter.inbound.web.hotel.dto.response.HotelResponse

interface HotelUseCase {
    fun createHotel(hotelSaveRequest: HotelSaveRequest)
    fun getHotels(): List<HotelResponse>
    fun reduceRoomQuantity(hotelId: Long)

}