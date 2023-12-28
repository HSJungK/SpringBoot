package com.example.model.jpa.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Table(name = "USER")
@Schema(name = "User data")
data class User(
    @Id
    @Column(name = "USER_ID", unique = true, nullable = false, updatable = false, length = 20)
    @Schema(description = "사용자 아이디", name = "id", maxLength = 20)
    val id: String,
    @Column(name = "USER_NAME", nullable = false, length = 10)
    @Schema(description = "사용자 이름", name = "name", maxLength = 10)
    val name: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false, length = 10)
    @Schema(description = "사용자 등급", name = "role")
    val role: UserRole
)

enum class UserRole {
    ADMIN,
    USER;
}

const val USER_ROLE_ENUMS = "[ADMIN, USER]"
const val USER_JSON_FORMAT = "{\"id\": \"string\", \"name\": \"string\", \"role\": \"enum$USER_ROLE_ENUMS\"}"


