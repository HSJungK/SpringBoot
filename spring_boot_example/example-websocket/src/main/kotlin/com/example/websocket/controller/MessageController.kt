package com.example.websocket.controller

import com.example.lib.model.Message
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.annotation.SubscribeMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/socket/v1")
class MessageController(val messageSendingOperations : SimpMessageSendingOperations) {

    @SubscribeMapping("/chatting/{roomId}")
    fun chattingSubscribe(
        @Headers headers: Map<String, String>,
        @Header("simpSessionId") sessionId: String,
        @DestinationVariable roomId: String,
    ) {
        println(" ===== Chatting Subscribe ===== ")
        println("Subscribe headers : $headers")
        println("sessionId : $sessionId, roomId : $roomId")
        sendMessage(Message(roomId = roomId, message = "Enter $sessionId"))
    }

    @MessageMapping("/chatting")
    fun chattingMessage(
        @Headers headers: Map<String, String>,
        message: Message,
    ) {
        println(" ===== Chatting Message ===== ")
        println("Subscription headers : $headers")
        sendMessage(message)
    }

    @MessageMapping("/chatting/{roomId}")
    fun chattingMessage(
        @Headers headers: Map<String, String>,
        @DestinationVariable roomId: String,
        message: Message,
    ) {
        println(" ===== Chatting Message ===== ")
        println("Subscription headers : $headers")
    }

    @PostMapping("/chatting")
    fun cattingPost(
        @RequestBody message: Message,
    ) {
        println(" ===== Chatting Post ===== ")
        println("message : $message")
        sendMessage(message)
    }

    private fun sendMessage(message: Message) {
        messageSendingOperations.convertAndSend("/chatting/${message.roomId}", message)
    }
}