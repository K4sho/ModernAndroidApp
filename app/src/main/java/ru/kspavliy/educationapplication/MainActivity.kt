package ru.kspavliy.educationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.kspavliy.educationapplication.ui.theme.EducationApplicationTheme
import ru.kspavliy.educationapplication.ui.training.InstagramProfileCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducationApplicationTheme {
                InstagramProfileCard()
            }
        }
    }
}

@Preview
@Composable
fun CardTest() {

}