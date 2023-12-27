package com.example.first.swagger.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "User data")
data class User(
        @Schema(description = "사용자 아이디", name = "id")
        val id: String,
        @Schema(description = "사용자 이름", name = "name")
        val name: String,
        @Schema(description = "사용자 등급", name = "role")
        val role: UserRole
)

enum class UserRole {
    ADMIN,
    USER;
}

const val USER_ROLE_ENUMS = "[ADMIN, USER]"
const val USER_JSON_FORMAT = "{\"id\": \"string\", \"name\": \"string\", \"role\": \"enum$USER_ROLE_ENUMS\"}"


