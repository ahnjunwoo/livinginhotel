package com.livinginhotel.application.port.inbound

import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingConfirmRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.response.BookingResponse

interface BookingUseCase {
    fun booking(bookingRequest: BookingRequest)
    fun bookingConfirm(bookingId: Long, bookingConfirmRequest: BookingConfirmRequest)
    fun getBookings(): List<BookingResponse>
}