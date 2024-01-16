package ru.kspavliy.educationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kspavliy.educationapplication.ui.theme.EducationApplicationTheme
import ru.kspavliy.educationapplication.ui.training.InstagramProfileCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducationApplicationTheme {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    InstagramProfileCard()
                }
            }
        }
    }
}

@Preview
@Composable
fun TestText() {
//    Text(
//        buildAnnotatedString {
//            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
//                append("Tak")
//            }
//            append("Hello")
//            append(" ")
//            append("Kirya")
//        }
//    )

}