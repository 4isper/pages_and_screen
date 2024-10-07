package com.example.lab5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun groupTasksByDate(tasks: List<Task>): Map<LocalDate, List<Task>> {
    return tasks.groupBy { it.date }
}

@Composable
fun Task6(modifier: Modifier) {
    val tasks = listOf(
        Task(LocalDate.now(), "Проверить почту"),
        Task(LocalDate.now(), "Созвон с командой"),
        Task(LocalDate.now().plusDays(1), "Закончить проект"),
        Task(LocalDate.now().plusDays(1), "Отправить отчёт"),
        Task(LocalDate.now().plusDays(2), "Сходить на встречу"),
        Task(LocalDate.now().plusDays(3), "Написать статью")
    )

    val groupedTasks = groupTasksByDate(tasks)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        groupedTasks.forEach { (date, tasksForDate) ->
            item {
                DateHeader(date)
            }
            items(tasksForDate) { task ->
                TaskItem(task)
            }
        }
    }
}

@Composable
fun DateHeader(date: LocalDate) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    Text(
        text = date.format(dateFormatter),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = task.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}
