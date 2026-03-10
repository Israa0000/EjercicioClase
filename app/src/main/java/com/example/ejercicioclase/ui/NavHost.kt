package com.example.ejercicioclase.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Splash"
    ) {
        composable("Splash") {
            SplashScreen(navController)
        }
        composable("Login") {
            EmailLoginScreen(navController)
        }
        composable("LoginDetail") {
            RegisterDetailScreen(navController)
        }

        composable("Home") {
            HomeScreen(navController)
        }

        composable("Productos") {
            ProductsScreen(navController)
        }

        composable("ProductHistory") {
            //TODO ProductHistoryScreen()
        }
        composable("Cart") {
            //TODO CartScreen()
        }
        composable("Profile") {
            //TODO ProfileScreen()
        }
        composable("LaLiga") {
            LaLigaScreen(navController)
        }

        composable(
            route = "Product/{id}",
            arguments= listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){backStackEntry ->
            val identifier = backStackEntry.arguments?.getInt("id")
            if (identifier != null) {
                ProductoDetailScreen(navController, identifier)
            }
        }
    }
}