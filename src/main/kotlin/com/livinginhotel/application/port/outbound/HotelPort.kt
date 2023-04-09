package com.livinginhotel.application.port.outbound

import com.livinginhotel.domain.hotel.Hotel

interface HotelPort {
    fun save(hotel: Hotel)
    fun getHotels(): List<Hotel>
    fun findById(hotelId: Long): Hotel?
}