package com.example.test

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.theme.TestTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

@Composable
fun LoginScreen(
    navController: NavController
){
    val auth = Firebase.auth
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            val emailValue = remember { mutableStateOf(TextFieldValue()) }
            val passwordValue = remember { mutableStateOf(TextFieldValue()) }
            Spacer(modifier = Modifier.padding(6.dp))
            Card(shape = RoundedCornerShape(20.dp), elevation = 10.dp, modifier = Modifier.padding(1.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.preview),
                    contentDescription = "login image",
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.6f)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                label = { Text("Email")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                value = emailValue.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f),
                onValueChange = {
                    emailValue.value = it
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                label = { Text("Password")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                value = passwordValue.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f),
                onValueChange = {
                    passwordValue.value = it
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.4f),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White),
                onClick = {
                    auth.createUserWithEmailAndPassword(
                        emailValue.value.text.trim(),
                        passwordValue.value.text.trim()
                    )
                        .addOnCompleteListener(){ task ->
                            if (task.isSuccessful){
                                Log.d("AUTH", "Success!")
                            } else {
                                Log.d("AUTH", "Failed: ${task.exception}")
                            }
                        }

                }) {
                Text(modifier = Modifier.clickable{
                    navController.navigate(route = Screen.Home.route)}, text = "Login",
                color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                modifier = Modifier.clickable{
                    navController.navigate(route = Screen.SignUp.route)
                },
                text = "Don't have an account? Sign Up",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
@Preview(name = "DayView")
fun LoginScreenLight(){
    TestTheme(darkTheme = false) {
        LoginScreen(
            navController = rememberNavController()
        )
    }
}

@Preview(name = "NightView", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenDark(){
    TestTheme(darkTheme = true) {
        LoginScreen(
            navController = rememberNavController()
        )
    }
}