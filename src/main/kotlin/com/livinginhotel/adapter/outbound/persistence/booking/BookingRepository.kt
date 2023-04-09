package com.livinginhotel.adapter.outbound.persistence.booking

import org.springframework.data.jpa.repository.JpaRepository

interface BookingRepository : JpaRepository<BookingJpaEntity, Long> {
}
