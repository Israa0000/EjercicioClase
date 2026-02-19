package com.example.ejercicioclase.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioClaseTheme {

            }
        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier = Modifier, ){
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {

            }
            Box(

            ){

            }
        }

    }

    @Composable
    fun Banner(){
        Card(
            modifier = Modifier.fillMaxWidth()
        ){
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Text(
                    text = "Banner"
                )
            }
        }
    }

    @Composable
    fun BottonNav() {
        Row(

        ){
            Box(

            ){
                Column(

                ){
                    Text(
                        text = "BottonNav"
                    )
                }
            }

        }
    }
    @Composable
    fun NumberTextField(label : String, value : String, onValueChange : (String) -> Unit ,modifier : Modifier = Modifier){//hacemos el proceso de abstraccion con esta funcion

        TextField(
            label = { Text(text = label) },
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier,
        )
    }

    @Composable
    fun ProductCarrusel() {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontal
        ) {
            Card(

            ) {

            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    EjercicioClaseTheme {
        Home().HomeScreen()
    }
}

