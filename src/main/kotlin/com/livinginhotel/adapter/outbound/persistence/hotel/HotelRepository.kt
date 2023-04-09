package com.livinginhotel.adapter.outbound.persistence.hotel

import org.springframework.data.jpa.repository.JpaRepository

interface HotelRepository : JpaRepository<HotelJpaEntity, Long> {

}
