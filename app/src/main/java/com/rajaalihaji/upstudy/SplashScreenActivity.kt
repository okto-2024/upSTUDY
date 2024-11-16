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
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rajaalihaji.upstudy.ui.theme.UpSTUDYTheme
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpSTUDYTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
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
fun Greeting2(name: String, modifier: Modifier = Modifier, context: Context = ComponentActivity()) {

    LaunchedEffect(key1 = true, block = {
        delay( 3500)
        context.startActivity(Intent(context, WelcomeScreenActivity::class.java))
    })
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF006BFF)),
        contentAlignment = Alignment.Center
        ) {
        Image(
            painter =painterResource(id = R.drawable.logo_up),
            contentDescription = "logo_up"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    UpSTUDYTheme {
        Greeting2("Android")
    }
}