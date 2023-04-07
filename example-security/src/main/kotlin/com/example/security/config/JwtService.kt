package com.example.security.config

import com.example.security.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function


@Service
class JwtService {

    @Value("\${application.security.jwt.secret-key}")
    private val secretKey: String? = null

    @Value("\${application.security.jwt.expiration}")
    private val jwtExpiration: Long = 0

    @Value("\${application.security.jwt.refresh-token.expiration}")
    private val refreshExpiration: Long = 0

    fun extractUserId(token: String): String{
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token!!)
        return claimsResolver.apply(claims)
    }

    fun generateToken(user: User): String {
        val map = HashMap<String, Any>()
        map.put("user", user)
        return generateToken(map, user)
    }

    fun generateToken(extraClaims: Map<String, Any>, user: User): String {
        return buildToken(extraClaims, user, jwtExpiration)
    }

    fun generateRefreshToken(user: User): String {
        return buildToken(HashMap(), user, refreshExpiration)
    }

    fun isTokenValid(token: String, user: User): Boolean {
        val userId: String = extractUserId(token)
        return userId == user.userId && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }

    private fun buildToken(extraClaims: Map<String, Any>, user: User, expiration: Long): String {
        return Jwts
            .builder()
            .setHeaderParam("type", "JWT")
            .setClaims(extraClaims)
            .setId(user.userId)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }


    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
    }

    private fun getSignInKey(): Key {
        val keyBytes: ByteArray = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}