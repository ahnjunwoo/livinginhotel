package com.livinginhotel.adapter.outbound.persistence.booking

import com.livinginhotel.domain.booking.BookingStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


@Entity
@Table(name = "booking")
class BookingJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "hotel_Id")
    val hotelId: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: BookingStatus,

    @CreationTimestamp
    @Column(name = "create_date_time")
    val createDateTime: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_date_time")
    val updateDateTime: LocalDateTime? = null
)