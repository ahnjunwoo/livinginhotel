package com.livinginhotel.restdocs

import com.livinginhotel.adapter.inbound.web.booking.BookingResource
import com.livinginhotel.application.port.inbound.BookingUseCase
import com.livinginhotel.fixtures.createBookingConfirmRequest
import com.livinginhotel.fixtures.createBookingListResponse
import com.livinginhotel.fixtures.createBookingRequest
import com.livinginhotel.fixtures.createHotelListResponse
import com.livinginhotel.restdocs.dsl.*
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.justRun
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(BookingResource::class)
class BookingRestDocs : BaseRestDocs() {

    @MockkBean
    private lateinit var bookingUseCase: BookingUseCase


    @Test
    fun `booking`() {
        justRun { bookingUseCase.booking(any()) }
        val result = mockMvcCall(
            post("/bookings").content(objectMapper.writeValueAsString(createBookingRequest()))
        )

        makeDocs(
            result = result,
            methodName = "{method_name}",
            resultMatcher = MockMvcResultMatchers.status().isCreated,
            requestFields = requestFields(
                "hotelId" type NUMBER means "호텔 아이디" isOptional false
            ),
            responseFields = responseFields(
                "code" type NUMBER means "응답코드",
                "message" type STRING means "응답메세지"
            )
        )
    }

    @Test
    fun `get-bookings`() {
        every { bookingUseCase.getBookings() } returns createBookingListResponse()

        val result = mockMvcCall(
            RestDocumentationRequestBuilders.get("/bookings")
        )

        makeDocs(
            result = result,
            methodName = "{method_name}",
            resultMatcher = MockMvcResultMatchers.status().isOk,
            responseFields = responseFields(
                "code" type NUMBER means "응답코드",
                "message" type STRING means "응답메세지",
                "results" type ARRAY means "호텔예약 목록",
                "results[].id" type NUMBER means "호텔예약 아이디" isOptional false,
                "results[].hotelId" type NUMBER means "호텔 아이디" isOptional false,
                "results[].status" type STRING means "예약상태" isOptional false,
                "results[].createDateTime" type DATE means "예약 신청일" isOptional false,
                "results[].updateDateTime" type DATE means "예약 상태 수정일" isOptional false,
            )
        )
    }

    @Test
    fun `booking-confirm`() {
        justRun { bookingUseCase.bookingConfirm(any(), any()) }
        val result = mockMvcCall(
            put("/bookings/{bookingId}/confirm", 1)
                .content(objectMapper.writeValueAsString(createBookingConfirmRequest()))
        )

        makeDocs(
            result = result,
            methodName = "{method_name}",
            resultMatcher = MockMvcResultMatchers.status().isOk,
            pathParameters = pathParameters(
                "bookingId" means "호텔 예약 아이디",
            ),
            requestFields = requestFields(
                "status" type STRING means "호텔 예약 상태값(REQUEST - 요청,APPROVAL - 승인, CANCEL - 반려[취소])" isOptional false
            ),
            responseFields = responseFields(
                "code" type NUMBER means "응답코드",
                "message" type STRING means "응답메세지"
            )
        )
    }
}