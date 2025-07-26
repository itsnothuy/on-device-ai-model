package com.example.myapplication.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreen(vm: ChatViewModel = viewModel()) {
    val uiState by vm.state.collectAsStateWithLifecycle()   // lifecycle-aware collector :contentReference[oaicite:7]{index=7}

    Scaffold(
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = uiState.currentInput,
                    onValueChange = vm::onInputChange,
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type…") }
                )
                IconButton(onClick = vm::onSendClicked) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        }
    ) { pv ->
        LazyColumn(
            reverseLayout = true,
            modifier = Modifier
                .fillMaxSize()
                .padding(pv)
        ) {
            items(uiState.messages.reversed()) { msg ->
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = if (msg.role == Role.USER)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = msg.text,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}