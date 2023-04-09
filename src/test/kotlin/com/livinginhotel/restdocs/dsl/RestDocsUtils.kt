package com.livinginhotel.restdocs.dsl

import org.springframework.restdocs.snippet.Attributes.Attribute

class RestDocsUtils {
    companion object {
        val DATETIME_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss"
        val DATE_FORMAT: String = "yyyy-MM-dd"

        fun defaultValue(value: String): Attribute {
            return Attribute(value, value)
        }

        fun customFormat(value: String): Attribute {
            return Attribute(value, value)
        }

        fun customSample(value: String): Attribute {
            return Attribute(value, value)
        }
    }
}
