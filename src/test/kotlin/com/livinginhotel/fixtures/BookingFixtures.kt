package com.livinginhotel.fixtures

import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingConfirmRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.response.BookingResponse
import com.livinginhotel.adapter.inbound.web.hotel.dto.request.HotelSaveRequest
import com.livinginhotel.adapter.inbound.web.hotel.dto.response.HotelResponse
import com.livinginhotel.domain.booking.Booking
import com.livinginhotel.domain.booking.BookingStatus
import com.livinginhotel.domain.hotel.HotelStatus
import java.time.LocalDateTime

fun createBookingRequest(): BookingRequest {
    return BookingRequest(
        hotelId = 2
    )
}

fun createBookingListResponse(): List<BookingResponse>{
    return listOf(
        BookingResponse(
            id = 1,
            hotelId = 1,
            status = BookingStatus.APPROVAL,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T20:19:47"),
        ),
        BookingResponse(
            id = 2,
            hotelId = 3,
            status = BookingStatus.CANCEL,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T20:19:47"),
        ),
        BookingResponse(
            id = 3,
            hotelId = 3,
            status = BookingStatus.REQUEST,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T20:19:47"),
        )
    )
}

fun createBookingConfirmRequest(): BookingConfirmRequest {
    return BookingConfirmRequest(
        status = BookingStatus.APPROVAL
    )
}

fun createBookings(): List<Booking>{
    return listOf(
        Booking(
            id = 1,
            hotelId = 1,
            status = BookingStatus.APPROVAL,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T20:19:47"),
        ),
        Booking(
            id = 2,
            hotelId = 3,
            status = BookingStatus.CANCEL,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T20:19:47"),
        ),
        Booking(
            id = 3,
            hotelId = 3,
            status = BookingStatus.REQUEST,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T20:19:47"),
        )
    )
}

fun createBooking(): Booking{
    return Booking(
        id = 1,
        hotelId = 1,
        status = BookingStatus.APPROVAL,
        createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
        updateDateTime = LocalDateTime.parse("2023-04-08T20:19:47"),
    )
}