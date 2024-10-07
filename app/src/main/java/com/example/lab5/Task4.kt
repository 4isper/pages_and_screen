package com.example.lab5

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task4(modifier: Modifier){
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember { derivedStateOf { DateTimeFormatter
        .ofPattern("dd.MM.yyyy")
        .format(pickedDate)} }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = pickedDate.toEpochDay() * 24 * 60 * 60 * 1000)
    var dateDialogController by  remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {dateDialogController = true }
        ) {
            Text("Pick Date")
        }
        Text("$formattedDate")

        if (dateDialogController) {
            DatePickerDialog(
                onDismissRequest = { dateDialogController = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let {
                            pickedDate = LocalDate.ofEpochDay(it / (24 * 60 * 60 * 1000))
                        }
                        Log.i("Data", formattedDate)
                        dateDialogController = false
                    }) {
                        Text(text = "Ok")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        dateDialogController = false
                    }) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}