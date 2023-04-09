package com.livinginhotel.application.service

import com.livinginhotel.adapter.inbound.web.hotel.dto.request.HotelSaveRequest
import com.livinginhotel.adapter.inbound.web.hotel.dto.response.HotelResponse
import com.livinginhotel.application.port.inbound.HotelUseCase
import com.livinginhotel.application.port.outbound.HotelPort
import com.livinginhotel.common.UseCase
import com.livinginhotel.domain.hotel.Hotel
import com.livinginhotel.domain.hotel.command.HotelSaveCommand
import com.livinginhotel.exception.ApiCode
import com.livinginhotel.exception.LivinginNonException
import org.modelmapper.ModelMapper
import org.springframework.transaction.annotation.Transactional

@UseCase
class HotelService(
    private val hotelPort: HotelPort,
    private val modelMapper: ModelMapper
) : HotelUseCase {
    @Transactional
    override fun createHotel(hotelSaveRequest: HotelSaveRequest) {
        val hotel = Hotel.createHotel(modelMapper.map(hotelSaveRequest, HotelSaveCommand::class.java))
        hotelPort.save(hotel)
    }

    @Transactional(readOnly = false)
    override fun getHotels(): List<HotelResponse> {
        return hotelPort.getHotels().map{
            modelMapper.map(it, HotelResponse::class.java)
        }
    }

    @Transactional
    override fun reduceRoomQuantity(hotelId: Long) {
        val hotel = hotelPort.findById(hotelId) ?: throw LivinginNonException(ApiCode.HOTEL_NOT_FOUND)
        hotel.reduceRoomQuantity()
        hotelPort.save(hotel)
    }

}