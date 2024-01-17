package ru.kspavliy.educationapplication.ui.vkscreens

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val selectedItemPosition = rememberSaveable {
                    mutableIntStateOf(0)
                }

                val items = listOf(
                    NavigationBarItem.Home,
                    NavigationBarItem.Favorite,
                    NavigationBarItem.Profile
                )
                items.forEachIndexed { index, navBarItem ->
                    NavigationBarItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        icon = { Icon(navBarItem.icon, contentDescription = null) },
                        label = { Text(text = stringResource(id = navBarItem.titleResId)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) {

    }
}