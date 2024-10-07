package com.example.lab5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task9() {
    var selectedItem by remember { mutableStateOf("Выберите элемент") }
    val menuItemsTop = listOf("Верхний элемент 1", "Верхний элемент 2", "Верхний элемент 3")
    val menuItemsBottom = listOf("Нижний элемент 1", "Нижний элемент 2", "Нижний элемент 3")

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                menuItemsBottom.forEach { item ->
                    TextButton(onClick = {
                        selectedItem = item
                        coroutineScope.launch { scaffoldState.bottomSheetState.show() }
                    }) {
                        Text(text = item)
                    }
                }
            }
        }
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(modifier = Modifier.padding(16.dp)) {
                    menuItemsTop.forEach { item ->
                        TextButton(onClick = {
                            selectedItem = item
                            coroutineScope.launch { drawerState.close() }
                        }) {
                            Text(text = item)
                        }
                    }
                }
            }
        ) {
            Scaffold(
                content = { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = selectedItem, style = MaterialTheme.typography.headlineMedium)
                    }
                }
            )
        }
    }
}