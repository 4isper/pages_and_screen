package com.example.lab5

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task10(modifier: Modifier){
    val context = LocalContext.current
    val itemsWithOptions = listOf(
        Pair("Item 1", listOf("Option A", "Option B")),
        Pair("Item 2", listOf("Option C", "Option D")),
        Pair("Item 3", listOf("Option E", "Option F")),
        Pair("Item 4", listOf("Option G", "Option H", "Option J")),
        Pair("Item 5", listOf("Option G", "Option H", "Option J")),
        Pair("Item 6", listOf("Option G", "Option H", "Option J")),
        Pair("Item 7", listOf("Option G", "Option H", "Option J")),
        Pair("Item 8", listOf("Option G", "Option H", "Option J")),
        Pair("Item 9", listOf("Option G", "Option H", "Option J")),
        Pair("Item 10", listOf("Option G", "Option H", "Option J")),
        Pair("Item 11", listOf("Option G", "Option H", "Option J")),
        Pair("Item 12", listOf("Option G", "Option H", "Option J")),
        Pair("Item 13", listOf("Option G", "Option H", "Option J")),
    )
    var expandedItemIndex by remember { mutableStateOf(-1) }

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(itemsWithOptions) { index, (item, options) ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(shape = RoundedCornerShape(30.dp))
                .clickable { expandedItemIndex = index }
                .background(color = Color.LightGray),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = item,
                    modifier = Modifier
                        .padding(start = 20.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black)

                if (expandedItemIndex == index) {
                    DropdownMenu(
                        expanded = true,
                        onDismissRequest = { expandedItemIndex = -1 }
                    ) {
                        options.forEach{ option ->
                            DropdownMenuItem(
                                text = { Text(option,
                                    fontSize = 15.sp) },
                                onClick = {
                                    println("$option selected for $item at index $index")
                                    Toast.makeText(context, "$option selected for $item", Toast.LENGTH_SHORT).show()
                                    expandedItemIndex = -1
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}