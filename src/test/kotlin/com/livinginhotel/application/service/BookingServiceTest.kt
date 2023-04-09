package com.livinginhotel.application.service

import com.livinginhotel.adapter.inbound.web.booking.dto.response.BookingResponse
import com.livinginhotel.adapter.inbound.web.hotel.dto.response.HotelResponse
import com.livinginhotel.application.port.inbound.HotelUseCase
import com.livinginhotel.application.port.outbound.BookingPort
import com.livinginhotel.application.port.outbound.HotelPort
import com.livinginhotel.config.ModelMapperConfig
import com.livinginhotel.exception.ApiCode
import com.livinginhotel.exception.LivinginNonException
import com.livinginhotel.fixtures.*
import com.livinginhotel.fixtures.createHotel
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify

class BookingServiceTest : BehaviorSpec({
    val bookingPort = mockk<BookingPort>()
    val hotelPort = mockk<HotelPort>()
    val hotelUseCase = mockk<HotelUseCase>()
    val modelMapper = ModelMapperConfig().modelMapper()

    val bookingService = BookingService(
        bookingPort, hotelPort,hotelUseCase,modelMapper
    )

    Given("존재하는 호텔에 대한 정상적인 예약을 하는경우") {
        every { hotelPort.findById(any()) } returns createHotel()
        justRun { bookingPort.booking(any()) }

        When("예약한다.") {
            bookingService.booking(createBookingRequest())

            Then("예약완료") {
                verify(exactly = 1) { bookingPort.booking(any()) }
            }
        }
    }

    Given("예약 요청시 객실 수량이 없는경우") {
        every { hotelPort.findById(any()) } returns createHotelRoomQuantityZero()
        justRun { hotelPort.save(any()) }

        When("예약한다.") {
            Then("예외를 발생한다.") {
                val exception = shouldThrow<LivinginNonException> { bookingService.booking(createBookingRequest()) }

                exception.code shouldBe ApiCode.HOTEL_NOT_EXIST.code.value()
                exception.message shouldBe ApiCode.HOTEL_NOT_EXIST.message
            }
        }
    }

    Given("호텔 예약 목록을 조회하는 경우") {
        val bookings = createBookings()
        every { bookingPort.getBookings() } returns bookings

        When("조회한다.") {
            val actual = bookingService.getBookings()

            Then("정상값으로 올바르게 조회되어져야 한다.") {
                actual shouldBe listOf(
                    BookingResponse(
                        id  = bookings[0].id,
                        hotelId = bookings[0].hotelId,
                        status = bookings[0].status,
                        createDateTime = bookings[0].createDateTime,
                        updateDateTime = bookings[0].updateDateTime
                    ),
                    BookingResponse(
                        id  = bookings[1].id,
                        hotelId = bookings[1].hotelId,
                        status = bookings[1].status,
                        createDateTime = bookings[1].createDateTime,
                        updateDateTime = bookings[1].updateDateTime
                    ),
                    BookingResponse(
                        id  = bookings[2].id,
                        hotelId = bookings[2].hotelId,
                        status = bookings[2].status,
                        createDateTime = bookings[2].createDateTime,
                        updateDateTime = bookings[2].updateDateTime
                    )
                )
            }
        }
    }

    Given("호텔 예약 승인을 하는 경우") {
        val booking = createBooking()
        every { bookingPort.findById(any()) } returns booking
        justRun { bookingPort.booking(booking) }
        justRun { hotelUseCase.reduceRoomQuantity(booking.hotelId) }

        When("승인한다.") {
            bookingService.bookingConfirm(booking.id, createBookingConfirmRequest())

            Then("정상적으로 승인이 되어야 한다.") {
                verify(exactly = 1) { bookingPort.booking(booking) }
                verify(exactly = 1) { hotelUseCase.reduceRoomQuantity(booking.hotelId) }
            }
        }
    }


})