package com.livinginhotel.restdocs

import com.livinginhotel.adapter.inbound.web.hotel.HotelResource
import com.livinginhotel.application.port.inbound.HotelUseCase
import com.livinginhotel.fixtures.createHotelListResponse
import com.livinginhotel.fixtures.createHotelRequest
import com.livinginhotel.restdocs.dsl.ARRAY
import com.livinginhotel.restdocs.dsl.BaseRestDocs
import com.livinginhotel.restdocs.dsl.NUMBER
import com.livinginhotel.restdocs.dsl.STRING
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.justRun
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(HotelResource::class)
class HotelRestDocs : BaseRestDocs() {

    @MockkBean
    private lateinit var hotelUseCase: HotelUseCase


    @Test
    fun `create-hotel`() {
        justRun { hotelUseCase.createHotel(any()) }
        val result = mockMvcCall(
            post("/hotels").content(objectMapper.writeValueAsString(createHotelRequest()))
        )

        makeDocs(
            result = result,
            methodName = "{method_name}",
            resultMatcher = MockMvcResultMatchers.status().isCreated,
            requestFields = requestFields(
                "name" type STRING means "호텔 이름" isOptional true,
                "roomQuantity" type NUMBER means "객실 수량" isOptional true
            ),
            responseFields = responseFields(
                "code" type NUMBER means "응답코드",
                "message" type STRING means "응답메세지"
            )
        )
    }

    @Test
    fun `get-hotels`() {
        every { hotelUseCase.getHotels() } returns createHotelListResponse()

        val result = mockMvcCall(
            get("/hotels")
        )

        makeDocs(
            result = result,
            methodName = "{method_name}",
            resultMatcher = MockMvcResultMatchers.status().isOk,
            responseFields = responseFields(
                "code" type NUMBER means "응답코드",
                "message" type STRING means "응답메세지",
                "results" type ARRAY means "호텔 목록",
                "results[].id" type NUMBER means "호텔아이디" isOptional false,
                "results[].name" type STRING means "호텔명" isOptional false,
                "results[].roomQuantity" type NUMBER means "객실수량" isOptional false,
                "results[].status" type STRING means "호텔 상태(EXIST - 재고있음, NOT_EXIST - 재고없음)" isOptional false,
            )
        )
    }


}