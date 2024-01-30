package ru.kspavliy.educationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.kspavliy.educationapplication.ui.theme.EducationApplicationTheme
import ru.kspavliy.educationapplication.ui.vkscreens.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EducationApplicationTheme {
                MainScreen()
            }
        }
    }
}