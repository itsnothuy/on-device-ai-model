package com.example.myapplication.chat

data class ChatUiState(
    val currentInput: String = "",
    val messages: List<Message> = emptyList()
)
