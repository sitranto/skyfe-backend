package com.skyfe.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
class JwtConfiguration(
    val key: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
    ) {
}