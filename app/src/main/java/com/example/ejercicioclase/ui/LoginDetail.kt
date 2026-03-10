package com.example.ejercicioclase.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme

@Composable
fun RegisterDetailScreen(navController: NavController, viewModel: LoginVM = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    val validacionNombre = uiState.nombre.isNotBlank()

    val validacionContraseña = uiState.contraseña.length >= 8 &&
            uiState.contraseña.any { it.isUpperCase() } &&
            uiState.contraseña.any { it.isLowerCase() } &&
            uiState.contraseña.any { it.isDigit() }

    val validacionTerminos = uiState.aceptarTerminos

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item { Spacer(modifier = Modifier.height(40.dp)) }

        item { HeaderRegister() }

        item { NameSection(viewModel) }

        item { PasswordSection(viewModel) }

        item { CumpleaniosSection(viewModel) }

        item { LegalCheckboxesSection(viewModel) }

        item { BotonRegistro(navController, isEnabled = (validacionNombre && validacionContraseña && validacionTerminos)) }

        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}

@Composable
fun HeaderRegister() {
    Column {
        Text(
            text = "MARCA",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Ahora vamos a convertirte en miembro de MARCA.",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp
        )
    }
}


@Composable
fun NameSection(viewModel: LoginVM = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = uiState.nombre,
            onValueChange = { viewModel.cambiarNombre(it)},
            modifier = Modifier.weight(1f),
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = uiState.apellido,
            onValueChange = { viewModel.cambiarApellido(it)},
            modifier = Modifier.weight(1f),
            label = { Text("Apellido") },
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text)
        )
    }
}

@Composable
fun PasswordSection(viewModel: LoginVM = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        OutlinedTextField(
            value = uiState.contraseña,
            onValueChange = { viewModel.cambiarContraseña(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            placeholder = { Text("••••••••") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (uiState.contraseña.length >= 8){ "✓ Mínimo 8 caracteres"}
                    else {"✕ Mínimo 8 caracteres"},
                    fontSize = 11.sp,
                    color = if (uiState.contraseña.length >= 8){Color(0xFF4CAF50)}
                    else {Color.Gray},
                    lineHeight = 16.sp
                )

                Text(
                    text = if (uiState.contraseña.any { it.isUpperCase() }){ "✓ Letra mayúscula"}
                    else {"✕ Letra mayúscula"},
                    fontSize = 11.sp,
                    color = if (uiState.contraseña.any { it.isUpperCase() }){Color(0xFF4CAF50)}
                    else {Color.Gray},
                    lineHeight = 16.sp
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (uiState.contraseña.any { it.isLowerCase() }){ "✓ Letra minúscula"}
                    else {"✕ Letra minúscula"},
                    fontSize = 11.sp,
                    color = if (uiState.contraseña.any { it.isLowerCase() }){Color(0xFF4CAF50)}
                    else {Color.Gray},
                    lineHeight = 16.sp
                )

                Text(
                    text = if (uiState.contraseña.any { it.isDigit() }){ "✓ Un número"}
                    else {"✕ Un número"},
                    fontSize = 11.sp,
                    color = if (uiState.contraseña.any { it.isDigit() }){Color(0xFF4CAF50)}
                    else {Color.Gray},
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun CumpleaniosSection(viewModel: LoginVM = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column {
        OutlinedTextField(
            value = uiState.cumpleanios,
            onValueChange = {viewModel.cambiarCumpleaños(it)},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Fecha de nacimiento") },
            placeholder = { Text("DD/MM/AAAA") },
            trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }
        )
        Text(
            text = "Obtén una recompensa de miembro de MARCA en tu cumpleaños",
            fontSize = 10.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}


@Composable
fun LegalCheckboxesSection(viewModel: LoginVM = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = uiState.recibirCorreos, onCheckedChange = { viewModel.toggleRecibirCorreos(it)})
            Text("Regístrate para recibir correos electrónicos con actualizaciones de MARCA sobre productos, ofertas y tus beneficios como miembro.", fontSize = 11.sp)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = uiState.aceptarTerminos, onCheckedChange = { viewModel.toggleAceptarTerminos(it)})
            Text("Acepto la Política de privacidad y los Términos de uso de MARCA.", fontSize = 11.sp)
        }
    }
}

@Composable
fun BotonRegistro(navController : NavController, isEnabled: Boolean) {
    Button(
        onClick = {
            navController.navigate("Home") {
                popUpTo("Login") {
                    inclusive = true
                }
            }
        },
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text("Crear una cuenta", color = Color.White, fontWeight = FontWeight.Bold)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPreview() {
    EjercicioClaseTheme {
        RegisterDetailScreen(NavController(LocalContext.current))
    }
}