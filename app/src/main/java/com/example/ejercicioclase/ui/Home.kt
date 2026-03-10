package com.example.ejercicioclase.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ejercicioclase.R
import com.example.ejercicioclase.data.UIState
import com.example.ejercicioclase.data.collections.Products
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


@Composable
fun HomeScreen(navController : NavController){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item{
                TopBar()
            }
            item{
                SectionTitle("Ofertas del día")
            }
            item{
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
            item{
                Banner()
            }
            item{
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
            item{
                ProductCorrousel(navController)
            }
        }


        //Haría de barra de navegación
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)

        ){
            BottomNav(navController)

        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd).padding(bottom = 70.dp, end = 10.dp)
        ){
            LigaButton(navController)
        }
    }
}

@Composable
fun Banner(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RectangleShape
    ) {
        //TODO Implementar imagen
    }
}

@Composable
fun ProductCorrousel(navController: NavController){
    val randomProducts = Products.productsList.shuffled().take(5)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(randomProducts){ product ->
            Card(
                modifier = Modifier
                    .width(160.dp)
                    .padding(4.dp),
                onClick = {
                    navController.navigate("Product/${product.id}")
                }
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Imagen proporcionada
                    Image(
                        painter = painterResource(product.image),
                        contentDescription = product.name,
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Nombre del producto
                    Text(
                        text = product.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Precios
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val precioNumerico = product.price.replace("$", "").toDoubleOrNull() ?: 0.0
                        val precioConDescuento = (precioNumerico * 0.8).toInt()

                        Text(
                            text = "$precioConDescuento$",
                            fontSize = 14.sp,
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = product.price,
                            fontSize = 14.sp,
                            color = Color.Red,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ProductSection(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 80.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(3){
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Sample product",
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
            )
        }
    }
}

@Composable
fun LigaButton(navController: NavController){
    FloatingActionButton(
        onClick = {
            navController.navigate("LaLiga")
        },
        containerColor = Color.Red,
        shape = CircleShape,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp),

        ) {
        Text(
            text = "Toda \n La Liga \n gratis",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(){
    EjercicioClaseTheme() {
        HomeScreen(NavController(LocalContext.current))
    }
}

class LoginVM : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    fun cambiarEmail(nuevoEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(email = nuevoEmail)
        }
    }

    fun cambiarNombre(nuevoNombre: String) {
        _uiState.update { currentState ->
            currentState.copy(nombre = nuevoNombre)
        }
    }

    fun cambiarApellido(nuevoApellido: String) {
        _uiState.update { currentState ->
            currentState.copy(apellido = nuevoApellido)
        }
    }

    fun cambiarContraseña(nuevaContraseña: String) {
        _uiState.update { currentState ->
            currentState.copy(contraseña = nuevaContraseña)
        }
    }

    fun cambiarCumpleaños(nuevaFecha: String) {
        _uiState.update { currentState ->
            currentState.copy(cumpleanios = nuevaFecha)
        }
    }

    fun toggleRecibirCorreos(valor: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(recibirCorreos = valor)
        }
    }

    fun toggleAceptarTerminos(valor: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(aceptarTerminos = valor)
        }
    }
}