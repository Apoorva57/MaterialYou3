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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.theme.TestTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignUpScreen(
    navController: NavController
){
    val auth = Firebase.auth
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimaryContainer),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            val nameValue = remember{ mutableStateOf(TextFieldValue())}
            val numberValue = remember{ mutableStateOf(TextFieldValue())}
            val emailValue = remember { mutableStateOf(TextFieldValue()) }
            val passwordValue = remember { mutableStateOf(TextFieldValue()) }
            Spacer(modifier = Modifier.padding(7.dp))
            Card(shape = RoundedCornerShape(20.dp), elevation = 10.dp, modifier = Modifier.padding(1.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.preview2),
                    contentDescription = "signup image",
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.4f)
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedTextField(label = {Text("Name")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = nameValue.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f),
                onValueChange = {
                    nameValue.value = it
                }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedTextField(label = {Text("Mobile number")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = numberValue.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f),
                onValueChange = {
                    numberValue.value = it
                }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedTextField(
                label = { Text("Email")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                value = emailValue.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9F),
                onValueChange = {
                    emailValue.value = it
                }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedTextField(
                label = { Text("Password")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary),
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
                    MaterialTheme.colorScheme.inversePrimary,
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

                })
            {
                Text( modifier = Modifier.clickable{
                    navController.navigate(route = Screen.Login.route)
                }, text = "Register",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
            modifier = Modifier.clickable{
                navController.navigate(route = Screen.Login.route)},
            text = "Already have an account? Login",
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        }
    }
}
@Composable
@Preview(name = "DayView")
fun SignUpScreenLight(){
    TestTheme(darkTheme = false) {
        SignUpScreen(navController = rememberNavController())
    }
}

@Preview(name = "NightView", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpScreenDark(){
    TestTheme(darkTheme = true) {
        SignUpScreen(
            navController = rememberNavController()
        )
    }
}