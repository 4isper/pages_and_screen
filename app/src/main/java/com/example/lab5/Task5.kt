package com.example.lab5

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task5(modifier: Modifier){
    var pickedTime by remember { mutableStateOf(LocalTime.now()) }
    var timeDialogController by  remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {timeDialogController = true}
        ) {
            Text("Pick Time")
        }
        Text("${pickedTime.hour}:${pickedTime.minute}")


        if(timeDialogController){
            val timePickerState = rememberTimePickerState(initialHour = pickedTime.hour,
                initialMinute = pickedTime.minute, is24Hour = true)

            TimePickerDialog(
                onDismiss = {timeDialogController = false},
                onConfirm = {
                    pickedTime = LocalTime.of(timePickerState.hour,
                        timePickerState.minute)
                    Log.i("Time", "$pickedTime")
                    timeDialogController = false
                }
            ){
                TimePicker(state = timePickerState)
            }
        }
    }
}


@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(
                onClick = {onDismiss()}
            ) { Text("Cancel") }
        },
        confirmButton = {
            TextButton(onClick = {onConfirm()}) {
                Text("Ok")
            }
        },
        text = {content()}
    )
}