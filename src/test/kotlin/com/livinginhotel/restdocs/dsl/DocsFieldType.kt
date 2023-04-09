package com.livinginhotel.restdocs.dsl

import org.springframework.restdocs.payload.JsonFieldType

sealed class DocsFieldType(
    val type: JsonFieldType
)

object ARRAY : DocsFieldType(JsonFieldType.ARRAY)
object STRING : DocsFieldType(JsonFieldType.STRING)
object DATE : DocsFieldType(JsonFieldType.STRING)
object DATETIME : DocsFieldType(JsonFieldType.STRING)
object NUMBER : DocsFieldType(JsonFieldType.NUMBER)
object NULL : DocsFieldType(JsonFieldType.NULL)
object BOOLEAN : DocsFieldType(JsonFieldType.BOOLEAN)
