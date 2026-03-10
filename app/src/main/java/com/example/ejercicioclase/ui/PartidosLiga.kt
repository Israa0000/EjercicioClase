package com.example.ejercicioclase.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ejercicioclase.data.Match
import com.example.ejercicioclase.data.collections.Matches
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme

@Composable
fun LaLigaScreen(navController: NavController){
    val matchesList = Matches.matchesList

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ) {
        Text(
            text = "Toda La Liga gratis",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(

        ) {
            items(matchesList){
                match -> LaLigaCard(match)
            }
        }

    }
}

@Composable
fun LaLigaCard(match : Match){
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = match.league,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = match.team1,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "VS",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = match.team2,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = match.timeOfMatch,
                color = Color.Green,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LaLigaScreenPreview(){
    EjercicioClaseTheme {
        LaLigaScreen(navController = NavController(LocalContext.current))
    }
}