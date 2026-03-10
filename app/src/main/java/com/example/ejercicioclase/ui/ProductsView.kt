package com.example.ejercicioclase.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ejercicioclase.data.Product
import com.example.ejercicioclase.data.collections.Products
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme

@Composable
fun ProductsScreen(navController: NavController){
    val productsList = Products.productsList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TopBar()

        Text(
            text = "Products CBD",
            style = MaterialTheme.typography.headlineLarge
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(productsList){
                product -> ProductCard(navController, product)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            BottomNav(navController)
        }

    }
}

//Provisional
@Composable
fun ProductCard(navController: NavController, product: Product){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = {
            navController.navigate("Product/${product.id}")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(product.image),
                contentDescription = product.name,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            )
            Text(
                text = product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val precioNumerico = product.price.replace("$", " ").toDoubleOrNull() ?: 0.0

                val precioConDescuento = (precioNumerico * 0.8).toInt()

                Text(
                    text = "$precioConDescuento$",
                    fontSize = 18.sp,
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = product.price,
                    fontSize = 18.sp,
                    color = Color.Red,
                    textDecoration = TextDecoration.LineThrough
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductsPreview(){
    EjercicioClaseTheme {
        ProductsScreen(navController = NavController(LocalContext.current))
    }
}