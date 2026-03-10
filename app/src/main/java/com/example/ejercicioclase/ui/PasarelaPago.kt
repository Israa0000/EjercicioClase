package com.example.ejercicioclase.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme

class PasarelaPago : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioClaseTheme {

            }
        }
    }
}
