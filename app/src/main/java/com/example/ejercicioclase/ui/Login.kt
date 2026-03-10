package com.example.ejercicioclase.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ejercicioclase.ui.LoginVM
import com.example.ejercicioclase.ui.theme.EjercicioClaseTheme

@Composable
fun EmailLoginScreen(navController: NavController, viewModel: LoginVM = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val validacionEmail = uiState.email.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        HeaderSection()

        Spacer(modifier = Modifier.height(32.dp))

        EmailInput(viewModel)

        Spacer(modifier = Modifier.weight(1f))

        FooterSection(navController, isEnabled = validacionEmail)
    }
}

@Composable
fun HeaderSection() {
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "MARCA",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Ingrese su correo electrónico para unirse a nosotros o iniciar sesión.",
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun EmailInput( viewModel: LoginVM = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.cambiarEmail(it)},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            placeholder = { Text("correo@ejemplo.com") },
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun FooterSection(navController: NavController, isEnabled: Boolean) {
    Button(
        onClick = {
            navController.navigate("LoginDetail")
        },
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text(
            text = "Siguiente",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginEmailPreview() {
    EjercicioClaseTheme {
        EmailLoginScreen(NavController(LocalContext.current))
    }
}