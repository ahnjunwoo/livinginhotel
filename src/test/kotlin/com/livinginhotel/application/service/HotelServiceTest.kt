package com.livinginhotel.application.service

import com.livinginhotel.adapter.inbound.web.hotel.dto.response.HotelResponse
import com.livinginhotel.application.port.outbound.HotelPort
import com.livinginhotel.config.ModelMapperConfig
import com.livinginhotel.exception.ApiCode
import com.livinginhotel.exception.LivinginNonException
import com.livinginhotel.fixtures.createHotel
import com.livinginhotel.fixtures.createHotelList
import com.livinginhotel.fixtures.createHotelRequest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify

class HotelServiceTest : BehaviorSpec({
    val hotelPort = mockk<HotelPort>()
    val modelMapper = ModelMapperConfig().modelMapper()

    val hotelService = HotelService(
        hotelPort, modelMapper
    )

    Given("호텔 정상 등록 하는 경우") {
        justRun { hotelPort.save(any()) }

        When("등록한다") {
            hotelService.createHotel(createHotelRequest())

            Then("정상적으로 호텔이 등록되어져야 한다.") {
                verify(exactly = 1) { hotelPort.save(any()) }
            }
        }
    }

    Given("호텔을 조회하는 경우") {
        val hotels = createHotelList()
        every { hotelPort.getHotels() } returns hotels

        When("조회한다.") {
            val actual = hotelService.getHotels()

            Then("정상값으로 올바르게 조회되어져야 한다.") {
                actual shouldBe listOf(
                    HotelResponse(
                        id = hotels[0].id,
                        name = hotels[0].name,
                        roomQuantity = hotels[0].roomQuantity,
                        status = hotels[0].status
                    ),
                    HotelResponse(
                        id = hotels[1].id,
                        name = hotels[1].name,
                        roomQuantity = hotels[1].roomQuantity,
                        status = hotels[1].status
                    ),
                    HotelResponse(
                        id = hotels[2].id,
                        name = hotels[2].name,
                        roomQuantity = hotels[2].roomQuantity,
                        status = hotels[2].status
                    )
                )
            }
        }
    }

    Given("호텔예약을 승인해야하는 경우") {
        val hotels = createHotel()
        every { hotelPort.findById(any()) } returns hotels
        justRun { hotelPort.save(any()) }

        When("특정 호텔에 대해 승인요청한다.") {
            hotelService.reduceRoomQuantity(hotels.id)

            Then("승인한 호텔에 객실 수량이 -1 감소되어져야 한다.") {
                verify(exactly = 1) { hotelPort.save(any()) }
            }
        }
    }

    Given("호텔예약을 승인할때 잘못된 호텔아이디를 넘긴경우") {
        every { hotelPort.findById(any()) } returns null
        justRun { hotelPort.save(any()) }

        When("객실수량 감소 요청시") {
            Then("예외를 발생한다.") {
                val exception = shouldThrow<LivinginNonException> { hotelService.reduceRoomQuantity(2) }

                exception.code shouldBe ApiCode.HOTEL_NOT_EXIST.code.value()
                exception.message shouldBe ApiCode.HOTEL_NOT_EXIST.message
            }
        }
    }
})