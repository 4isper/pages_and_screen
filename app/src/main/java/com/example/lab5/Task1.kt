package com.example.lab5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Task1(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen/1") {
        composable("screen/{stackDepth}") { backStackEntry ->
            val stackDepth = backStackEntry.arguments?.getString("stackDepth") ?: "1"
            Screen(modifier = modifier, navController = navController, stackDepth.toInt())
        }
    }
}

@Composable
fun Screen(modifier: Modifier, navController: NavHostController, currentStackDepth: Int) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Глубина стека: $currentStackDepth",
            fontSize = 35.sp)

        Button(onClick = {
            val nextDepth = currentStackDepth + 1
            navController.navigate("screen/$nextDepth")
        },
            modifier = Modifier.padding(5.dp)) {
            Text("Вперёд", fontSize = 35.sp)
        }
        Button(onClick = {
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
            }
        }) {
            Text("Назад",fontSize = 35.sp)
        }
    }
}