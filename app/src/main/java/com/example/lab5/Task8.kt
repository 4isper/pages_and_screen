package com.example.lab5

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun Task8(modifier: Modifier) {

    val tasks =  listOf(
        Task(LocalDate.now(), "Задача: Дописать отчет"),
        Task(LocalDate.now().plusDays(1), "Задача: Митинг с командой"),
        Task(LocalDate.now().plusDays(2), "Задача: Prepare presentation"),
        Task(LocalDate.now().plusDays(3), "Задача: Созвон с клиентом"),
        Task(LocalDate.now().plusDays(4), "Задача: Review code"),
        Task(LocalDate.now().plusDays(5), "Задача: Написание документации"),
        Task(LocalDate.now().plusDays(6), "Задача: Submit проекта")
    )

    val listState = rememberLazyListState()

    LazyRow(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        state = listState,
        flingBehavior = rememberSnapFlingBehavior(listState)
    ) {
        items(tasks) { task ->
            TaskSlide(task)
        }
    }
}

@Composable
fun TaskSlide(task: Task) {
    Box(
        modifier = Modifier
            .size(300.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = task.date.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = task.description,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}