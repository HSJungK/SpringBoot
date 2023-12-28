package com.example.model.jpa.repository

import com.example.model.jpa.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
}