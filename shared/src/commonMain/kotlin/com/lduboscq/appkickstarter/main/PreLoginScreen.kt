package com.lduboscq.appkickstarter.main

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.ui.Image
import kotlinx.coroutines.delay

class PreLoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val images = listOf(
            "https://i.pinimg.com/564x/af/8d/6b/af8d6bbeb09aa6841ac6731fea04c1fb.jpg",
            "https://i.pinimg.com/564x/06/63/35/06633580042be8404a4862b8a61e8b2f.jpg",
            "https://i.pinimg.com/564x/5f/ea/4a/5fea4adff315b2724936eb5ec217a7d9.jpg"
        )
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedBackgroundImages(images = images)
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = {
                        navigator.push(ScreenRouter(AllScreens.Login))
                    },
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colors.onPrimary),
                ) {
                    Text(
                        "Login",
                        style = MaterialTheme.typography.button,
                        modifier = Modifier.padding(top = 8.dp, start = 30.dp, end = 30.dp, bottom = 8.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun AnimatedBackgroundImages(images: List<String>) {
    var currentIndex by remember { mutableStateOf(0) }

    val currentImage by remember(images, currentIndex) {
        mutableStateOf(images[currentIndex])
    }

    val transition = updateTransition(currentImage, label = "backgroundImageTransition")
    val imageModifier = Modifier.fillMaxSize()

    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000) }
    ) { image ->
        if (image == currentImage) 1f else 0f
    }

    Box(modifier = imageModifier) {
        Image(
            url = currentImage,
            contentDescription = null,
            modifier = imageModifier.alpha(alpha).fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }

    // Animate to the next image
    LaunchedEffect(currentIndex) {
        delay(3000) // Delay between image transitions
        currentIndex = (currentIndex + 1) % images.size
    }
}