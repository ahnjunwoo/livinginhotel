package com.livinginhotel.application.service

import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingConfirmRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.response.BookingResponse
import com.livinginhotel.application.port.inbound.BookingUseCase
import com.livinginhotel.application.port.inbound.HotelUseCase
import com.livinginhotel.application.port.outbound.BookingPort
import com.livinginhotel.application.port.outbound.HotelPort
import com.livinginhotel.common.UseCase
import com.livinginhotel.domain.booking.Booking
import com.livinginhotel.domain.booking.BookingStatus
import com.livinginhotel.domain.hotel.HotelStatus
import com.livinginhotel.exception.ApiCode
import com.livinginhotel.exception.LivinginNonException
import org.modelmapper.ModelMapper
import org.springframework.transaction.annotation.Transactional

@UseCase
class BookingService(
    private val bookingPort: BookingPort,
    private val hotelPort: HotelPort,
    private val hotelUseCase: HotelUseCase,
    private val modelMapper: ModelMapper
) : BookingUseCase {
    @Transactional
    override fun booking(bookingRequest: BookingRequest) {
        val hotel = hotelPort.findById(bookingRequest.hotelId) ?: throw LivinginNonException(ApiCode.HOTEL_NOT_FOUND)
        if (hotel.status == HotelStatus.NOT_EXIST || hotel.roomQuantity == 0) throw LivinginNonException(ApiCode.HOTEL_NOT_EXIST)
        bookingPort.booking(Booking.booking(bookingRequest.hotelId))
    }

    @Transactional
    override fun bookingConfirm(bookingId: Long, bookingConfirmRequest: BookingConfirmRequest) {
        val booking = bookingPort.findById(bookingId) ?: throw LivinginNonException(ApiCode.BOOKING_NOT_FOUND)
        booking.confirm(bookingConfirmRequest.status)

        if (bookingConfirmRequest.status == BookingStatus.APPROVAL) {
            hotelUseCase.reduceRoomQuantity(booking.hotelId)
        }

        bookingPort.booking(booking)
    }

    override fun getBookings(): List<BookingResponse> {
        return bookingPort.getBookings().map {
            modelMapper.map(it, BookingResponse::class.java)
        }
    }


}