package com.example.security.auth

import com.example.security.config.JwtService
import com.example.security.token.Token
import com.example.security.token.TokenRepository
import com.example.security.token.TokenType
import com.example.security.user.User
import com.example.security.user.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthenticationService(
    val userRepository: UserRepository,
    val tokenRepository: TokenRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager
) {

    fun register(id: String, password: String): AuthenticationResponse {
        val user = User(id, password)
        val savedUser = userRepository.save(user)
        val jwtToken = jwtService.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)
//        saveUserToken(savedUser, jwtToken)
        return AuthenticationResponse(accessToken = jwtToken, refreshToken = refreshToken)
    }

//    fun authenticate(request: AuthenticationRequest): AuthenticationResponse? {
//        authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(
//                request.getEmail(),
//                request.getPassword()
//            )
//        )
//        val user: Unit = repository.findByEmail(request.getEmail())
//            .orElseThrow()
//        val jwtToken = jwtService.generateToken(user)
//        val refreshToken: Unit = jwtService.generateRefreshToken(user)
//        revokeAllUserTokens(user)
//        saveUserToken(user, jwtToken)
//        return AuthenticationResponse.builder()
//            .accessToken(jwtToken)
//            .refreshToken(refreshToken)
//            .build()
//    }

    private fun saveUserToken(user: User, jwtToken: String) {
        val token: Token = Token(
//            user = user,
            id = user.userId,
            token = jwtToken,
            tokenType = TokenType.BEARER,
            revoked = false,
            expired = false,
        )
        tokenRepository.save(token)
    }

//    private fun revokeAllUserTokens(user: User) {
//        val validUserTokens: Unit = tokenRepository.findAllValidTokenByUser(user.getId())
//        if (validUserTokens.isEmpty()) return
//        validUserTokens.forEach { token ->
//            token.setExpired(true)
//            token.setRevoked(true)
//        }
//        tokenRepository.saveAll(validUserTokens)
//    }
//
//    @Throws(IOException::class)
//    fun refreshToken(
//        request: HttpServletRequest,
//        response: HttpServletResponse
//    ) {
//        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
//        val refreshToken: String
//        val userEmail: String?
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return
//        }
//        refreshToken = authHeader.substring(7)
//        userEmail = jwtService.extractUsername(refreshToken)
//        if (userEmail != null) {
//            val user: Unit = this.repository.findByEmail(userEmail)
//                .orElseThrow()
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                val accessToken = jwtService.generateToken(user)
//                revokeAllUserTokens(user)
//                saveUserToken(user, accessToken)
//                val authResponse: Unit = AuthenticationResponse.builder()
//                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
//                    .build()
//                ObjectMapper().writeValue(response.outputStream, authResponse)
//            }
//        }
//    }
}