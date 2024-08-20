package com.example.chikapignagna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chikapignagna.ui.theme.ChikapignagnaTheme
//dependencias de navegacion
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//se supone que a partir de aca comienza tod0, esta es la entrada principal de la app
class MainActivity : ComponentActivity() {
    //onCreate es para inicializar la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge sirve para habilitar el disenno de borde a borde, para que la UI use la pantalla completa
        enableEdgeToEdge()
        //esto define la composicion que se renderiza en pantalla, en el caso concreto configuramos el tema de la app y mostramos el LoginScreen
        setContent {
            //Aplicamos el tema de Chikapignagna
            ChikapignagnaTheme {
                //configuramos el navcontroller para la navegacion entre pantallas
                val navController = rememberNavController()
                //navhost es el conponente de navegacion que contiene las pantallas
                NavHost(navController = navController, startDestination = "login") {
                    //primera pantalla - login
                    composable("login") {
                        LoginScreen(onLoginSuccess = { email ->
                            //navegacion hacia la pantalla de bienvenida al iniciar sesion con exito
                            navController.navigate("welcome/$email")
                        },
                            onNavigateToRegister = {
                                navController.navigate("register")
                            })
                    }
                    composable("register") {
                        RegistroScreen(
                            onNavigateToLogin = {
                                navController.popBackStack("login", inclusive = false)
                            }
                        )
                    }
                    //pantalla de bienvenida
                    composable("welcome/{email}") { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""
                        WelcomeScreen(email = email, onLogout = {
                            //al cerrar sesion se vuelve al login
                            navController.popBackStack("login", inclusive = false)
                        })
                    }

                }

                //esta me interesa que es Scaffold
                //Es un componente de Material Design  que proporciona una estructura basica para la UI, permitiendo agregar barras superiores, inferiores
                //botones flotantes, entre otros, supongo que como un sistema de grid
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    //LoginScreen es la pantalla de inicio, el padding es para mantener los margenes adecuados
//                    LoginScreen(modifier = Modifier.padding(innerPadding))
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainLoginScreenPreview() {
    ChikapignagnaTheme {
        LoginScreen(onLoginSuccess = {}, onNavigateToRegister = {})
    }
}