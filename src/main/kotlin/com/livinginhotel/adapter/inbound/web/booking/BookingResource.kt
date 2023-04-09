package com.livinginhotel.adapter.inbound.web.booking

import com.livinginhotel.adapter.inbound.web.base.BaseResponse
import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingConfirmRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.request.BookingRequest
import com.livinginhotel.adapter.inbound.web.booking.dto.response.BookingResponse
import com.livinginhotel.application.port.inbound.BookingUseCase
import com.livinginhotel.common.WebAdapter
import com.livinginhotel.exception.ApiCode
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter
class BookingResource(
    private val bookingUseCase: BookingUseCase
) {

    @PostMapping("/bookings")
    fun booking(@RequestBody @Valid bookingRequest: BookingRequest): ResponseEntity<BaseResponse<String>> {
        bookingUseCase.booking(bookingRequest)
        return ResponseEntity(BaseResponse(ApiCode.SUCCESS), HttpStatus.CREATED)
    }

    @PutMapping("/bookings/{bookingId}/confirm")
    fun bookingConfirm(
        @PathVariable("bookingId") bookingId: Long,
        @RequestBody bookingConfirmRequest: BookingConfirmRequest
    ): ResponseEntity<BaseResponse<String>> {
        bookingUseCase.bookingConfirm(bookingId, bookingConfirmRequest)
        return ResponseEntity(BaseResponse(ApiCode.SUCCESS), HttpStatus.OK)
    }

    @GetMapping("/bookings")
    fun getBookings(): ResponseEntity<BaseResponse<List<BookingResponse>>> {
        return ResponseEntity(BaseResponse(bookingUseCase.getBookings()), HttpStatus.OK)
    }
}