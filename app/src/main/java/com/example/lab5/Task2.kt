package com.example.lab5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Task2(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "firstPage") {
        composable("firstPage") { FirstPage(modifier,navController) }
        composable("secondPage") { SecondPage(modifier,navController) }
    }
}

@Composable
fun FirstPage(modifier: Modifier,navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Первая страница")

        Button(onClick = {
            navController.navigate("secondPage")
        }) {
            Text("Добавить страницу")
        }

        Button(onClick = {
            navController.popBackStack("secondPage", inclusive = true)
        }) {
            Text("Убрать страницу")
        }
    }
}

@Composable
fun SecondPage(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Вторая страница")

        Button(onClick = {
            navController.navigate("firstPage") {
                launchSingleTop = true
            }
        }) {
            Text("Вернуться на первую страницу")
        }
    }
}