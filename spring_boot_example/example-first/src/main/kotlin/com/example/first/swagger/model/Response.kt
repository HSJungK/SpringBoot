package com.example.first.swagger.model

import com.example.first.swagger.model.ResponseContents.Code.NO_USER_CODE
import com.example.first.swagger.model.ResponseContents.Code.OK_CODE
import com.example.first.swagger.model.ResponseContents.Code.USER_EXISTS_CODE
import com.example.first.swagger.model.ResponseContents.Message.NO_USER_MESSAGE
import com.example.first.swagger.model.ResponseContents.Message.OK_MESSAGE
import com.example.first.swagger.model.ResponseContents.Message.USER_EXISTS_MESSAGE
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema


fun okResponse(data: Any? = null) =
        Response(code = ResponseType.OK.code, message = ResponseType.OK.message, data = data)
fun errorResponse(responseType: ResponseType) =
        Response(code = responseType.code, message = responseType.message, data = null)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response(
        @Schema(description = "응답 코드", name = "code")
        val code: Int,
        @Schema(description = "응답 메세지", name = "message")
        val message: String,
        @Schema(description = "응답 데이타", name = "data")
        val data: Any?
)

enum class ResponseType(val code: Int, val message: String) {
    OK(OK_CODE, OK_MESSAGE),
    USER_EXISTS(USER_EXISTS_CODE, USER_EXISTS_MESSAGE),
    NO_USER(NO_USER_CODE, NO_USER_MESSAGE)
}

object ResponseContents {
    object Code {
        const val OK_CODE = 0
        const val USER_EXISTS_CODE = 1001
        const val NO_USER_CODE = 1002
    }

    object Message {
        const val OK_MESSAGE = "OK"
        const val USER_EXISTS_MESSAGE = "User exists"
        const val NO_USER_MESSAGE = "No user"
    }

    object SwaggerExamples {

        const val OK_RESPONSE_NAME = "code: $OK_CODE, message: $OK_MESSAGE"

        // OK response without data
        const val OK_RESPONSE_NO_DATA_VALUE = "{\"code\": $OK_CODE,\"message\": \"$OK_MESSAGE\"}"

        // OK response with data
        const val OK_RESPONSE_USERS = "{\"code\": $OK_CODE,\"message\": \"$OK_MESSAGE\"," +
                "\"data\":[$USER_JSON_FORMAT]}"
        const val OK_RESPONSE_USER = "{\"code\": $OK_CODE,\"message\": \"$OK_MESSAGE\"," +
                "\"data\":$USER_JSON_FORMAT}"

        // Error response
        const val USER_EXISTS_NAME = "code: $USER_EXISTS_CODE, message: $USER_EXISTS_MESSAGE"
        const val USER_EXISTS_VALUE= "{\"code\": $USER_EXISTS_CODE,\"message\": \"$USER_EXISTS_MESSAGE\"}"

        const val NO_USER_NAME = "code: $NO_USER_CODE, message: $NO_USER_MESSAGE"
        const val NO_USER_VALUE = "{\"code\": $NO_USER_CODE,\"message\": \"$NO_USER_MESSAGE\"}"
    }
}