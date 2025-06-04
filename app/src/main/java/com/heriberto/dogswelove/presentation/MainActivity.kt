package com.heriberto.dogswelove.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.heriberto.dogswelove.presentation.ui.screens.doglist.DogListScreen
import com.heriberto.dogswelove.ui.theme.DogsWeLoveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DogsWeLoveTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DogListScreen()
                }
            }
        }
    }
}