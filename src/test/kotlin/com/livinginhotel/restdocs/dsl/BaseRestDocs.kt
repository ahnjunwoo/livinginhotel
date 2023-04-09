package com.livinginhotel.restdocs.dsl

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.*
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.QueryParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import java.nio.charset.Charset
import kotlin.reflect.KClass

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
open class BaseRestDocs {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    fun mockMvcCall(
        method: MockHttpServletRequestBuilder,
        contentType: MediaType = MediaType.APPLICATION_JSON,
    ): ResultActions {
        val builder = method.contentType(contentType).characterEncoding(Charset.defaultCharset())

        return mockMvc.perform(
            builder
        )
    }

    infix fun String.type(docsFieldType: DocsFieldType): Field {
        val field = createField(this, docsFieldType.type)
        when (docsFieldType) {
            is DATE -> field formattedAs RestDocsUtils.DATE_FORMAT
            is DATETIME -> field formattedAs RestDocsUtils.DATETIME_FORMAT
            else -> {}
        }
        return field
    }

    infix fun String.means(desc: String): ParameterField {
        return createParameterField(this, desc)
    }

    private fun createField(value: String, type: JsonFieldType, optional: Boolean = false): Field {
        val descriptor = fieldWithPath(value)
            .type(type)
            .description("")

        if (optional) descriptor.optional()

        return Field(descriptor)
    }

    private fun createParameterField(name: String, desc: String, optional: Boolean = false): ParameterField {
        val descriptor = parameterWithName(name)
            .description(desc)

        if (optional) descriptor.optional()

        return ParameterField(descriptor)
    }

    infix fun <T : Enum<T>> String.type(enumFieldType: ENUM<T>): Field {
        val field = createField(this, JsonFieldType.STRING, false)
        field.format = enumFieldType.enums.toString()
        return field
    }

    data class ENUM<T : Enum<T>>(val enums: Collection<T>) : DocsFieldType(JsonFieldType.STRING) {
        constructor(clazz: KClass<T>) : this(clazz.java.enumConstants.asList())
    }

    protected fun requestHeaders(vararg field: Field): RequestHeadersSnippet {
        return HeaderDocumentation.requestHeaders(
            field.map {
                HeaderDocumentation.headerWithName(it.descriptor.path).description(it.descriptor.description)
            }
        )
    }

    protected fun requestFields(vararg field: Field): RequestFieldsSnippet {
        return PayloadDocumentation.requestFields(field.map { it.descriptor })
    }

    protected fun queryParameters(vararg parameterField: ParameterField): QueryParametersSnippet {
        return RequestDocumentation.queryParameters(parameterField.map { it.descriptor })
    }

    protected fun pathParameters(vararg parameterField: ParameterField): PathParametersSnippet {
        return RequestDocumentation.pathParameters(parameterField.map { it.descriptor })
    }

    protected fun responseFields(vararg field: Field): ResponseFieldsSnippet {
        val fieldDescriptors = field.map {
            val fieldDescriptor =
                fieldWithPath(it.descriptor.path).description(it.descriptor.description).type(it.descriptor.type)
            if (it.descriptor.isOptional) {
                fieldDescriptor.optional()
            }
            fieldDescriptor
        }

        return PayloadDocumentation.responseFields(fieldDescriptors)
    }

    protected fun makeDocs(
        result: ResultActions,
        methodName: String,
        resultMatcher: ResultMatcher? = null,
        queryParameters: QueryParametersSnippet? = null,
        pathParameters: PathParametersSnippet? = null,
        requestHeaders: RequestHeadersSnippet? = null,
        requestFields: RequestFieldsSnippet? = null,
        responseFields: ResponseFieldsSnippet? = null
    ) {

        if (resultMatcher != null) {
            result.andExpect(resultMatcher)
        }
        result
            .andDo(MockMvcResultHandlers.print())
            .andDo(
                MockMvcRestDocumentationWrapper.document(
                    methodName,
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    *listOf(
                        queryParameters,
                        requestHeaders,
                        requestFields,
                        responseFields,
                        pathParameters
                    ).mapNotNull {
                        it
                    }.toTypedArray()
                )
            )
    }
}
