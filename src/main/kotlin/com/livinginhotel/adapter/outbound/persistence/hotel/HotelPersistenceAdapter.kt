package com.livinginhotel.adapter.outbound.persistence.hotel

import com.livinginhotel.application.port.outbound.HotelPort
import com.livinginhotel.common.PersistenceAdapter
import com.livinginhotel.domain.hotel.Hotel
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull

@PersistenceAdapter
class HotelPersistenceAdapter(
    private val hotelRepository: HotelRepository,
    private val modelMapper: ModelMapper
) : HotelPort {
    override fun save(hotel: Hotel) {
        val hotels =  modelMapper.map(hotel, HotelJpaEntity::class.java)
        hotelRepository.save(hotels)
    }

    override fun getHotels(): List<Hotel> {
        return hotelRepository.findAll().map{
            modelMapper.map(it, Hotel::class.java)
        }
    }

    override fun findById(hotelId: Long): Hotel? {
        return hotelRepository.findByIdOrNull(hotelId)?.let {
            modelMapper.map(it, Hotel::class.java)
        }
    }

}
