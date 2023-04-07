package com.example.security.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {



    @PostMapping("/register")
    fun register(id: String, password: String): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.register(id, password))
    }

//    @PostMapping("/authenticate")
//    fun authenticate(
//        @RequestBody request: AuthenticationRequest?
//    ): ResponseEntity<AuthenticationResponse?>? {
//        return ResponseEntity.ok(service.authenticate(request))
//    }
//
//    @PostMapping("/refresh-token")
//    @Throws(IOException::class)
//    fun refreshToken(
//        request: HttpServletRequest?,
//        response: HttpServletResponse?
//    ) {
//        service.refreshToken(request, response)
//    }

}