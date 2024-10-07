package com.example.lab5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Task3(modifier: Modifier){
    var showDialog by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = displayText)
        Button(
            onClick = { showDialog = true}
        ) {
            Text("Open Dialog")
        }

        if (showDialog){
            AlertDialog(
                onDismissRequest = {showDialog = false},
                dismissButton = {
                    TextButton(
                        onClick = {showDialog = false}
                    ) { Text("Cancel") }
                },
                confirmButton = {
                    TextButton(onClick = {
                        displayText = inputText
                        showDialog = false
                    }) {
                        Text("Ok")
                    }
                },
                text = {
                    TextField(
                        value = inputText,
                        onValueChange = {inputText = it},
                        placeholder = { Text(text = "Введите текст")}
                    )
                }
            )
        }
    }
}
