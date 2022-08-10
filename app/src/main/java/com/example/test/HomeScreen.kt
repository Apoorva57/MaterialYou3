package com.example.test

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.theme.TestTheme

@Composable
fun HomeScreen(
    navController: NavController
){
    Column(Modifier.fillMaxSize()){
        Text(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onTertiary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "Dynamic color display using Material You 3")
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1F)
            .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center){
            Text(text = "Primary Color", color = MaterialTheme.colorScheme.onPrimary, fontSize = 20.sp)
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1F)
            .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center){
            Text(text = "On Primary Color", color = MaterialTheme.colorScheme.primary, fontSize = 20.sp)
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1F)
            .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center){
            Text(text = "Secondary Color", color = MaterialTheme.colorScheme.onSecondary, fontSize = 20.sp)
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1F)
            .background(MaterialTheme.colorScheme.onSecondary),
            contentAlignment = Alignment.Center){
            Text(text = "On Secondary Color", color = MaterialTheme.colorScheme.secondary, fontSize = 20.sp)
        }
    }
}
@Composable
@Preview(name = "DayView")
fun HomeScreenLight(){
    TestTheme(darkTheme = false) {
        HomeScreen(navController = rememberNavController())
    }
}

@Preview(name = "NightView", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDark(){
    TestTheme(darkTheme = true) {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}