package com.example.lib.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Message(
    @JsonProperty("roomId")
    val roomId: String,
    @JsonProperty("message")
    val message: String
)