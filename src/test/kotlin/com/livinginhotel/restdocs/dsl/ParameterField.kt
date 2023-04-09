package com.livinginhotel.restdocs.dsl

import org.springframework.restdocs.request.ParameterDescriptor

open class ParameterField(
    val descriptor: ParameterDescriptor,
) {
    protected open var default: String
        get() = descriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_DEFAULT_VALUE, "") as String
        set(value) {
            descriptor.attributes(RestDocsUtils.defaultValue(value))
        }

    protected open var format: String
        get() = descriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_FORMAT, "") as String
        set(value) {
            descriptor.attributes(RestDocsUtils.customFormat(value))
        }

    protected open var sample: String
        get() = descriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_SAMPLE, "") as String
        set(value) {
            descriptor.attributes(RestDocsUtils.customSample(value))
        }

    open infix fun means(value: String): ParameterField {
        return description(value)
    }

    private fun description(value: String): ParameterField {
        this.descriptor.description(value)
        return this
    }

    open infix fun attributes(block: ParameterField.() -> Unit): ParameterField {
        block()
        return this
    }

    open infix fun withDefaultValue(value: String): ParameterField {
        this.default = value
        return this
    }

    open infix fun formattedAs(value: String): ParameterField {
        this.format = value
        return this
    }

    open infix fun example(value: String): ParameterField {
        this.sample = value
        return this
    }

    open infix fun isOptional(value: Boolean): ParameterField {
        if (value) descriptor.optional()
        return this
    }

    open infix fun isIgnored(value: Boolean): ParameterField {
        if (value) descriptor.ignored()
        return this
    }
}
