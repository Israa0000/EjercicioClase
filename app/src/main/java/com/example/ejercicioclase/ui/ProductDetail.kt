package com.example.ejercicioclase.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ejercicioclase.data.collections.Products
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme

@Composable
fun ProductoDetailScreen(navController: NavController, productId: Int
) {
    val producto = Products.productsList.first { it.id == productId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Botón volver
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen del producto
        Image(
            painter = painterResource(producto.image),
            contentDescription = producto.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Nombre
        Text(
            text = producto.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Descripción
        Text(
            text = producto.description,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Precio
        Text(
            text = producto.price,
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF2E7D32)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Categoría
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Text(
                text = producto.categoria.name,
                modifier = Modifier.padding(
                    horizontal = 12.dp,
                    vertical = 6.dp
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón añadir al carrito
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32)
            )
        ) {
            Text("Añadir al carrito")
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomNav(navController = navController)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewProductoDetailScreen(){
    EjercicioClaseTheme() {
        ProductoDetailScreen(NavController(LocalContext.current), 4)
    }
}





