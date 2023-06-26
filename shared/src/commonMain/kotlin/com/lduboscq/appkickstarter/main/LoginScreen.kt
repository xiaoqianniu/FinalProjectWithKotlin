package com.lduboscq.appkickstarter.main

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_onPrimary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_primary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_light_secondary
import com.lduboscq.appkickstarter.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


/**
 * Represents the login screen.
 * 646240160192-tm0s4r2rnt9q6irmo3a05pgvgannfh05.apps.googleusercontent.com
 * 646240160192-6fluksqseop07doqln856tog0d9ist7e.apps.googleusercontent.com
 * https://github.com/stevdza-san/OneTapCompose
 */
class LoginScreen : Screen {

    @Composable
    override fun Content() {

        val screenModel =
            rememberScreenModel() { LoginScreenModel(LoginRepositoryRealmLocal()) }
        val state by screenModel.state.collectAsState()
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isClicked by remember { mutableStateOf(true) }
        val navigator = LocalNavigator.currentOrThrow
        var errorMessage by remember { mutableStateOf("") }


        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                url = "https://i.pinimg.com/564x/9d/36/fd/9d36fd94e51bdb73759070905718e669.jpg",
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()

            )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.Center)
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 8.dp,
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        backgroundColor = Color.White,
                    ) {

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)
                                .wrapContentHeight(Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally,

                            content = {
                                when (val result = state) {
                                    is LoginScreenModel.State.Init -> Text("...")
                                    is LoginScreenModel.State.Loading -> Text("Loading")
                                    is LoginScreenModel.State.Result -> {
                                        Text("Success")
                                    }

                                    else -> {
                                        Text("Invalid email or password", color = Color.Red)
                                    }
                                }
                                ShinyText(
                                    text = "LoginMe",
                                    modifier = Modifier.padding(vertical = 50.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
//                            Text(
//                                "LoginMe",
//                                color = MaterialTheme.colors.onSecondary,
//                                fontSize = 30.sp,
//                                style = MaterialTheme.typography.h3,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier.padding(vertical = 50.dp),
//                                textAlign = TextAlign.Center
//                            )


                                OutlinedTextField(
                                    value = email,
                                    onValueChange = { email = it },
                                    textStyle = TextStyle(textAlign = TextAlign.Center),
                                    label = { Text("Enter email address") },
                                    leadingIcon = {
                                        Icon(Icons.Filled.Email, contentDescription = "email icon")
                                    },
                                    singleLine = true,
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        textColor = MaterialTheme.colors.onSurface,
                                        focusedBorderColor = MaterialTheme.colors.primary,
                                        unfocusedBorderColor = MaterialTheme.colors.onSurface
                                    )
                                )

                                Spacer(modifier = Modifier.height(15.dp))
                                OutlinedTextField(
                                    value = password,
                                    onValueChange = { password = it },
                                    textStyle = TextStyle(textAlign = TextAlign.Center),
                                    label = { Text("Enter password") },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Lock,
                                            contentDescription = "password icon"
                                        )
                                    },
                                    visualTransformation = PasswordVisualTransformation(),
                                    singleLine = true,
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        textColor = MaterialTheme.colors.onSurface,
                                        focusedBorderColor = MaterialTheme.colors.primary,
                                        unfocusedBorderColor = MaterialTheme.colors.onSurface
                                    )
                                )
                                Spacer(modifier = Modifier.height(15.dp))

                                Text(
                                    "New user? Click here",
                                    modifier = Modifier.padding(10.dp)
                                        .clickable(onClick = {
                                            navigator.push(
                                                ScreenRouter(
                                                    AllScreens.Register
                                                )
                                            )
                                        }),
                                    color = md_theme_light_secondary,
                                    textDecoration = TextDecoration.Underline,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Button(
                                    onClick = {
                                        val result = runBlocking {
                                            screenModel.login(email, password)
                                        }
                                        when (result) {
                                            is LoginScreenModel.LoginResult.Success -> {
                                                // Navigate to profile screen
                                                navigator.push(ScreenRouter(AllScreens.Profile(email)))
                                            }

                                            is LoginScreenModel.LoginResult.Error -> {
                                                // Show error message
                                                errorMessage = "Invalid credentials"
                                            }
                                        }

                                    }, modifier = Modifier.padding(20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        md_theme_dark_primary
                                    ),

                                    enabled = !email.isEmpty() && !password.isEmpty()
                                ) {

                                    Text("Login")
                                }
                                Text("or sign in with")
                                Spacer(modifier = Modifier.height(15.dp))
                                Image(
                                    url = "https://cdn-icons-png.flaticon.com/512/281/281764.png", // Replace with the URL of the Google logo image
                                    modifier = Modifier
                                        .height(40.dp)
                                        .width(40.dp)
                                        .clickable(onClick = {})
                                )

                            }
                        )
                    }
                }

            }


        }

}





/**
 * Changes the color based on whether the button is clicked or not.
 *
 * @param isClicked Determines whether the button is clicked or not.
 * @return The color to be used for the text.
 */
@Composable
fun colorChangeByClick(isClicked: Boolean): Color {

    return if (isClicked) {
        MaterialTheme.colors.onSecondary
    } else {
        MaterialTheme.colors.error
    }
}
@Composable
fun ShinyText(text: String, modifier: Modifier = Modifier) {
    var isShowing by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            isShowing = !isShowing
        }
    }

    val shimmerColor = animateColorAsState(
        targetValue = if (isShowing) MaterialTheme.colors.onSecondary else Color.Transparent,
        animationSpec = tween(durationMillis = 500)
    )

    Text(
        text = text,
        color = shimmerColor.value,
        fontSize = 30.sp,
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}
