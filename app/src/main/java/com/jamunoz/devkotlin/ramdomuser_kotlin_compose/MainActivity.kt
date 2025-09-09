package com.jamunoz.devkotlin.ramdomuser_kotlin_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.UserViewModel
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.ui.theme.RamdomUserKotlinComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RamdomUserKotlinComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                     MyApp1()
                }
            }
        }
    }
}

@Composable
fun MyApp1(
        viewModel: UserViewModel= hiltViewModel()
) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RamdomUserKotlinComposeTheme {
        MyApp1()
    }
}