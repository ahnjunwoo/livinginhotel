package com.livinginhotel.adapter.inbound.web.hotel

import com.livinginhotel.adapter.inbound.web.base.BaseResponse
import com.livinginhotel.adapter.inbound.web.hotel.dto.request.HotelSaveRequest
import com.livinginhotel.adapter.inbound.web.hotel.dto.response.HotelResponse
import com.livinginhotel.application.port.inbound.HotelUseCase
import com.livinginhotel.common.WebAdapter
import com.livinginhotel.exception.ApiCode
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter
class HotelResource(
    private val hotelUseCase: HotelUseCase
) {
    @PostMapping("/hotels")
    fun createHotel(@RequestBody @Valid hotelSaveRequest: HotelSaveRequest): ResponseEntity<BaseResponse<String>> {
        hotelUseCase.createHotel(hotelSaveRequest)
        return ResponseEntity(BaseResponse(ApiCode.SUCCESS), HttpStatus.CREATED)
    }

    @GetMapping("/hotels")
    fun getHotels(): ResponseEntity<BaseResponse<List<HotelResponse>>> {
        return ResponseEntity(BaseResponse(hotelUseCase.getHotels()), HttpStatus.OK)
    }

}