package com.rajaalihaji.upstudy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajaalihaji.upstudy.ui.theme.UpSTUDYTheme

class LoginActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            UpSTUDYTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        authViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier, authViewModel: AuthViewModel) {

    LoginScreen(authViewModel = authViewModel)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    UpSTUDYTheme {
        Greeting3("Android")
    }
}

@Composable
fun LoginScreen(authViewModel: AuthViewModel) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> {
                // masuk ke home
                context.startActivity(Intent(context, MainActivity::class.java))
            }
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            // .background(Brush.verticalGradient(colors = listOf(Color.Blue, Color.LightGray)))
            //.padding(16.dp)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(250.dp)
                .heightIn(min = 50.dp)
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(Color(0xFF3AA5FF), Color.Blue)))

        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight(500),
                color = Color.Yellow
            )
        }

        Box(modifier = Modifier
            .heightIn(min = 50.dp)
            .fillMaxSize()
            .background(Color.Blue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ice_email),
                            contentDescription = "Email Icon"
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_lock),
                            contentDescription = "Lock Icon"
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { /* Handle forgot password */ }) {
                    Text(text = "Forgot Password?")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    TextButton(onClick = { /* Handle create account */ }) {
                        Text(text = "Create Account")
                    }
                    // Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {

                        authViewModel.login(email,password)
                        // context.startActivity(Intent(context, MainActivity::class.java))
                    }) {
                        Text(text = "Login")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Version 1.0")
            }
        }
    }
}
