package com.example.service

import com.example.model.ResponseType
import com.example.model.errorResponse
import com.example.model.jpa.entity.User
import com.example.model.jpa.repository.UserRepository
import com.example.model.okResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun get() = ResponseEntity.ok(okResponse(userRepository.findAll()))

    fun post(user: User) = if (exist(user.id)) {
        ResponseEntity.ok(errorResponse(ResponseType.USER_EXISTS))
    } else {
        userRepository.save(user)
        ResponseEntity.ok(okResponse(user))
    }

    fun put(user: User) = if (exist(user.id)) {
        userRepository.save(user)
        ResponseEntity.ok(okResponse(user))
    } else {
        ResponseEntity.ok(errorResponse(ResponseType.NO_USER))
    }

    fun delete(id: String) = if (exist(id)) {
        userRepository.deleteById(id)
        ResponseEntity.ok(okResponse())
    } else {
        ResponseEntity.ok(errorResponse(ResponseType.NO_USER))
    }

    private fun exist(id: String) = userRepository.findById(id).isPresent
}