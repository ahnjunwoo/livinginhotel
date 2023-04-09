package com.livinginhotel.fixtures

import com.livinginhotel.adapter.inbound.web.hotel.dto.request.HotelSaveRequest
import com.livinginhotel.adapter.inbound.web.hotel.dto.response.HotelResponse
import com.livinginhotel.domain.hotel.Hotel
import com.livinginhotel.domain.hotel.HotelStatus
import java.time.LocalDateTime

fun createHotelRequest(): HotelSaveRequest {
    return HotelSaveRequest(
        name = "서울드래곤시티-이비스스타일 앰배서더 서울 용산",
        roomQuantity = 5
    )
}

fun createHotelListResponse(): List<HotelResponse> {
    return listOf(
        HotelResponse(
            id = 1,
            name = "서울드래곤시티-이비스스타일 앰배서더 서울 용산",
            roomQuantity = 4,
            status = HotelStatus.EXIST
        ),
        HotelResponse(
            id = 2,
            name = "라마다 서울 동대문",
            roomQuantity = 0,
            status = HotelStatus.NOT_EXIST
        ),
        HotelResponse(
            id = 3,
            name = "디어스 명동",
            roomQuantity = 2,
            status = HotelStatus.EXIST
        )
    )
}

fun createHotelList(): List<Hotel> {
    return listOf(
        Hotel(
            id = 1,
            name = "서울드래곤시티-이비스스타일 앰배서더 서울 용산",
            roomQuantity = 4,
            status = HotelStatus.EXIST,
            version = 1,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T19:20:47")
        ),
        Hotel(
            id = 2,
            name = "라마다 서울 동대문",
            roomQuantity = 0,
            status = HotelStatus.NOT_EXIST,
            version = 2,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T19:20:47")
        ),
        Hotel(
            id = 3,
            name = "디어스 명동",
            roomQuantity = 2,
            status = HotelStatus.EXIST,
            version = 0,
            createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
            updateDateTime = LocalDateTime.parse("2023-04-08T19:20:47")
        )
    )
}

fun createHotel(): Hotel {
    return Hotel(
        id = 1,
        name = "서울드래곤시티-이비스스타일 앰배서더 서울 용산",
        roomQuantity = 4,
        status = HotelStatus.EXIST,
        version = 1,
        createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
        updateDateTime = LocalDateTime.parse("2023-04-08T19:20:47")
    )
}

fun createHotelRoomQuantityZero(): Hotel {
    return Hotel(
        id = 1,
        name = "서울드래곤시티-이비스스타일 앰배서더 서울 용산",
        roomQuantity = 0,
        status = HotelStatus.NOT_EXIST,
        version = 1,
        createDateTime = LocalDateTime.parse("2023-04-08T19:19:47"),
        updateDateTime = LocalDateTime.parse("2023-04-08T19:20:47")
    )
}

