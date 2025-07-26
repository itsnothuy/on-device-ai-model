package com.example.myapplication.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class ChatViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChatUiState())
    val state: StateFlow<ChatUiState> = _state

    fun onInputChange(text: String) =
        _state.update { it.copy(currentInput = text) }

    fun onSendClicked() = viewModelScope.launch(Dispatchers.IO) {
        val prompt = _state.value.currentInput.trim()
        if (prompt.isEmpty()) return@launch

        // optimistic UI update
        _state.update {
            it.copy(currentInput = "", messages = it.messages + Message(Role.USER, prompt))
        }

        // TODO call LLM → replace with placeholder for now
        val reply = "Echo: $prompt"
        _state.update { it.copy(messages = it.messages + Message(Role.ASSISTANT, reply)) }
    }
}