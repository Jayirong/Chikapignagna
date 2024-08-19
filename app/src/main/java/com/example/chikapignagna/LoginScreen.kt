package com.example.chikapignagna

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chikapignagna.ui.theme.ChikapignagnaTheme


//este composale define la pantalla de inicio de sesion
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    //Variables :P, rememberSaveable sirve para que las variables conserven su estado en cosas como rotacion de pantalla
    var email by rememberSaveable { mutableStateOf("")}
    var contrasena by rememberSaveable { mutableStateOf("")}
    var contraVisible by rememberSaveable { mutableStateOf(false)}

    //Column ayuda a organizar los cosos de forma vertical, a partir de aca nos centramos en la UI
    Column(
        modifier = modifier
            .fillMaxSize() //FillMaxSize indica que ocupe tod0 el espacio disponible
            .padding(16.dp), //padding de 16dp
        verticalArrangement =  Arrangement.Center, //centra el contenido verticalmente
        horizontalAlignment = Alignment.CenterHorizontally //centra el contenido horizontalmente
    ) {
        //Texto que hace de encabezado para la pantalla de inicio de sesion
        Text(text = "Iniciar Sesión", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 32.dp)) //el ultimo nnade espacio debajo del texto

        //Campo de texto para el email
        OutlinedTextField(
            value = email, //valor actual del campo
            onValueChange = { email = it }, //actualiza la variable email cuando el usuario ingresa texto
            label = { Text("Correo Electrónico") }, //etiqueta dentro del campo
            modifier = Modifier.fillMaxWidth() //indica que el campo use el espacio disponible
        )

        //espacio entre campos
        Spacer(modifier = Modifier.height(16.dp))

        //campo de texto para la contra
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña")},
            modifier = Modifier.fillMaxWidth(),
            //esto controla que la contra est[e invisible o visible
            visualTransformation =  if (contraVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = { //este es el icono de ojito
                val image = if (contraVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

                IconButton(onClick = { contraVisible = !contraVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        //boton de iniciar sesion, falta una funcionalidad real
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text ="Ingresar")
        }

        //boton de texto
        TextButton(onClick = {}) {
            Text(text = "¿Olvidaste tu contraseña?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //boton para crear nueva cuenta
        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
           Text(text = "Crear nueva cuenta")
        }
    }
}

//disclaimer, soi tonto, estuve usando el emulador tod0 el rato,
//el preview habilita el mostrar como se ver[a el dise;o  de la pantalla en android studio
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ChikapignagnaTheme {
        LoginScreen()
    }
}