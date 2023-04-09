package com.livinginhotel.adapter.outbound.persistence.booking

import com.livinginhotel.application.port.outbound.BookingPort
import com.livinginhotel.common.PersistenceAdapter
import com.livinginhotel.domain.booking.Booking
import com.livinginhotel.domain.hotel.Hotel
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull

@PersistenceAdapter
class BookingPersistenceAdapter(
    private val bookingRepository: BookingRepository,
    private val modelMapper: ModelMapper
) : BookingPort {
    override fun booking(booking: Booking) {
        bookingRepository.save(modelMapper.map(booking,BookingJpaEntity::class.java))
    }

    override fun findById(bookingId: Long): Booking? {
        return bookingRepository.findByIdOrNull(bookingId)?.let {
            modelMapper.map(it, Booking::class.java)
        }
    }

    override fun getBookings(): List<Booking> {
        return bookingRepository.findAll().map{
            modelMapper.map(it, Booking::class.java)
        }
    }


}
