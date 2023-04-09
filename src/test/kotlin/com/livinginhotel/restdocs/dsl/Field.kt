package com.livinginhotel.restdocs.dsl

import org.springframework.restdocs.payload.FieldDescriptor

open class Field(
    val descriptor: FieldDescriptor,
) {
    protected open var default: String
        get() = descriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_DEFAULT_VALUE, "") as String
        set(value) {
            descriptor.attributes(RestDocsUtils.defaultValue(value))
        }

    open var format: String
        get() = descriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_FORMAT, "") as String
        set(value) {
            descriptor.attributes(RestDocsUtils.customFormat(value))
        }

    protected open var sample: String
        get() = descriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_SAMPLE, "") as String
        set(value) {
            descriptor.attributes(RestDocsUtils.customSample(value))
        }

    open infix fun means(value: String): Field {
        return description(value)
    }

    private fun description(value: String): Field {
        this.descriptor.description(value)
        return this
    }

    open infix fun attributes(block: Field.() -> Unit): Field {
        block()
        return this
    }

    open infix fun withDefaultValue(value: String): Field {
        this.default = value
        return this
    }

    open infix fun formattedAs(value: String): Field {
        this.format = value
        return this
    }

    open infix fun example(value: String): Field {
        this.sample = value
        return this
    }

    open infix fun isOptional(value: Boolean): Field {
        if (value) descriptor.optional()
        return this
    }

    open infix fun isIgnored(value: Boolean): Field {
        if (value) descriptor.ignored()
        return this
    }
}
