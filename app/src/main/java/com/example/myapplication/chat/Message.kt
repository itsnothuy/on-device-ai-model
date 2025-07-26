package com.example.myapplication.chat

data class Message(
    val role: Role,
    val text: String,
)

enum class Role { USER, ASSISTANT }