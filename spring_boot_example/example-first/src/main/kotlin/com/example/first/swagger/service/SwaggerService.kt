package com.example.first.swagger.service

import com.example.first.swagger.model.ResponseType
import com.example.first.swagger.model.User
import com.example.first.swagger.model.errorResponse
import com.example.first.swagger.model.okResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SwaggerService {

    private val data = hashMapOf<String, User>()

    fun get() = ResponseEntity.ok(okResponse(data.values.toList()))

    fun post(user: User) = if (data.containsKey(user.id)) {
        ResponseEntity.ok(errorResponse(ResponseType.USER_EXISTS))
    } else {
        data[user.id] = user
        ResponseEntity.ok(okResponse(user))
    }

    fun put(user: User) = if (data.containsKey(user.id)) {
        data[user.id] = user
        ResponseEntity.ok(okResponse(user))
    } else {
        ResponseEntity.ok(errorResponse(ResponseType.NO_USER))
    }

    fun delete(id: String) = if (data.containsKey(id)) {
        val user = data.remove(id)
        println("User is removed - $user")
        ResponseEntity.ok(okResponse())
    } else {
        ResponseEntity.ok(errorResponse(ResponseType.NO_USER))
    }
}