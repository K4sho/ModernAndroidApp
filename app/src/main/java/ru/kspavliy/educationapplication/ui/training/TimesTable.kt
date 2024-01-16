package ru.kspavliy.educationapplication.ui.training

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** Отображение таблицы умножения в разноцветном виде */
@Composable
fun TimesTable() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        for (i in 1..9) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                for (digit in 1..9) {
                    val color = if ((i + digit) % 2 == 0) {
                        Color.Yellow
                    } else {
                        Color.White
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(color = color)
                            .border(width = 1.dp, color = Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${i * digit}")
                    }
                }
            }
        }
    }
}