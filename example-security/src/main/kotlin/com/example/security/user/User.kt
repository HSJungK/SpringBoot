package com.example.security.user

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "USER")
class User(

    @Id
    @Column(name = "USER_ID")
    val userId: String? = null,

    @Column(name = "PASSWORD")
    private val password: String? = null,

    @Column(name = "NAME")
    private val name: String? = null): UserDetails {

    @Enumerated(EnumType.STRING)
    private val role: Role ?= Role.USER

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(SimpleGrantedAuthority(role?.name))

    override fun getPassword(): String = this.password.toString()

    override fun getUsername(): String = this.userId.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}

