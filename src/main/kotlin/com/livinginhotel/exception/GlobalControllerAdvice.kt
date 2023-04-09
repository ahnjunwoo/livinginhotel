package com.livinginhotel.exception

import jakarta.validation.ConstraintViolationException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {

    companion object : KLogging()

    @ExceptionHandler(Exception::class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknownException(e: Exception): ResponseEntity<ExceptionResponse> {
        logger.error("알 수 없는 오류 발생 message: ${e.message}", e)
        return ResponseEntity(
            ExceptionResponse(ApiCode.INTERNAL_SERVER_ERROR),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    fun httpRequestMethodNotSupportedExceptionHandler(e: Exception) =
        ResponseEntity(
            ExceptionResponse(ApiCode.METHOD_NOT_ALLOWED),
            HttpStatus.METHOD_NOT_ALLOWED
        )

    @ExceptionHandler(LivinginNonException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun livinginExceptionHandler(e: LivinginNonException) =
        ExceptionResponse(
            code = e.code,
            message = e.message.toString()
        )


    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException) =
        ExceptionResponse(
            code = HttpStatus.BAD_REQUEST.value(),
            message = e.bindingResult.fieldError?.defaultMessage ?: "알 수 없는 에러",
        )


    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationError(e: ConstraintViolationException): ExceptionResponse {
        logger.error { "잘못된 요청값 : ${e.constraintViolations.map { it.message }}" }
        return ExceptionResponse(
            code = HttpStatus.BAD_REQUEST.value(),
            message = e.message ?: "알 수 없는 에러",
        )
    }

}
