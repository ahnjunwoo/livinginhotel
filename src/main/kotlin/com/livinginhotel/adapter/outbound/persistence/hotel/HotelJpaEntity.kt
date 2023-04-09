package com.livinginhotel.adapter.outbound.persistence.hotel

import com.livinginhotel.domain.hotel.HotelStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


@Entity
@Table(name = "hotel")
class HotelJpaEntity (
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   val id: Long = 0,

   @Column(name = "name",nullable = false)
   val name: String,

   @Enumerated(EnumType.STRING)
   @Column(name = "status",nullable = false)
   val status: HotelStatus = HotelStatus.EXIST,

   @Column(name = "room_quantity")
   val roomQuantity: Int = 1,

   @Version
   @Column(name = "version")
   val version: Long? = null,

   @CreationTimestamp
   @Column(name = "create_date_time")
   val createDateTime: LocalDateTime? = null,

   @UpdateTimestamp
   @Column(name = "update_date_time")
   val updateDateTime: LocalDateTime? = null,
 )