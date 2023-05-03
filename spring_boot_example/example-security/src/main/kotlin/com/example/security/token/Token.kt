package com.example.security.token

import jakarta.persistence.*

@Entity
class Token(
    @Id
    @GeneratedValue
    var id: String? = null,

    @Column(unique = true)
    var token: String? = null,

    @Enumerated(EnumType.STRING)
    var tokenType: TokenType = TokenType.BEARER,

    var revoked: Boolean = false,

    var expired: Boolean = false) {

}