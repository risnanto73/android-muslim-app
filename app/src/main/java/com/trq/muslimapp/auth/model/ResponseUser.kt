package com.trq.muslimapp.auth.model

class ResponseUser(
    val status: Int? = null,
    val message: String? = null,
    val user: DataUser? = null,
    val token: Token? = null
)

class DataUser(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val role: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)


class Token(
    val token: String? = null
)


