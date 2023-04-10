package com.livinginhotel.exception

import org.springframework.http.HttpStatus

enum class ApiCode(
    val code: HttpStatus,
    val message: String
) {
    SUCCESS(HttpStatus.OK, "성공"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않는 메소드 형식입니다."),
    BOOKING_NOT_FOUND(HttpStatus.NOT_FOUND, "호텔 예약내역을 찾을수 없습니다."),
    HOTEL_NOT_FOUND(HttpStatus.NOT_FOUND, "호텔을 찾을수 없습니다."),
    HOTEL_NOT_EXIST(HttpStatus.BAD_REQUEST, "호텔 객실 수량이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
}
