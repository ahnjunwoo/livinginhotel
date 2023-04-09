package com.livinginhotel.application.port.outbound

import com.livinginhotel.domain.booking.Booking

interface BookingPort {
    fun booking(booking: Booking)
    fun findById(bookingId: Long): Booking?
    fun getBookings(): List<Booking>
}