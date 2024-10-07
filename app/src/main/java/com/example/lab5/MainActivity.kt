package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MainMenu) {
        composable(Routes.MainMenu){ Menu(modifier, navController) }
        composable(Routes.Task+"1"){ Task1(modifier) }
        composable(Routes.Task+"2"){ Task2(modifier) }
        composable(Routes.Task+"3"){ Task3(modifier) }
        composable(Routes.Task+"4"){ Task4(modifier) }
        composable(Routes.Task+"5"){ Task5(modifier) }
        composable(Routes.Task+"6"){ Task6(modifier) }
        composable(Routes.Task+"7"){ Task7(modifier, "https://youtube.com/@4isper") }
        composable(Routes.Task+"8"){ Task8(modifier) }
        composable(Routes.Task+"9"){ Task9() }
        composable(Routes.Task+"10"){ Task10(modifier) }
    }
}

@Composable
fun Menu(modifier: Modifier = Modifier, navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        for (i in 1..10){
            Button(
                modifier = Modifier.padding(5.dp).fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.DarkGray
                ),
                onClick = {navController.navigate(Routes.Task+"$i")},
            ){
                Text("Task $i", fontSize = 30.sp)
            }
        }
    }
}