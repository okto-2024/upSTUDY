package com.rajaalihaji.upstudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rajaalihaji.upstudy.ui.theme.UpSTUDYTheme
import kotlinx.coroutines.delay

class WelcomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpSTUDYTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        this
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier, context: Context = ComponentActivity()) {

    LaunchedEffect(key1 = true, block = {
        delay( 3000)
        context.startActivity(Intent(context, LoginActivity2::class.java))
    })
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF006BFF)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "welcome"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    UpSTUDYTheme {
        Greeting3("Android")
    }
}