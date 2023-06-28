package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.appkickstarter.shared.SharedRes
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_onPrimary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_primary
import com.lduboscq.appkickstarter.ui.Image


@Composable
fun MyBottomBar() {
    val navigator = LocalNavigator.currentOrThrow
    BottomAppBar(
        modifier = Modifier.fillMaxWidth().height(72.dp),
    backgroundColor = Color.White,
        elevation = 10.dp, // Add elevation to the BottomAppBar for a raised appearance
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp), // Adjust the padding as per your needs


    ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = {
                    navigator.push(ScreenRouter(AllScreens.Login))
                }) {
                    Image(
                        url = "https://icons.iconarchive.com/icons/custom-icon-design/pretty-office-4/256/home-icon.png",
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }



                IconButton(onClick = {
                    navigator.push(ScreenRouter(AllScreens.Login))
                }) {
                    Image(
                        url = "https://cdn-icons-png.flaticon.com/512/1041/1041373.png",
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }



                IconButton(onClick = {
                    navigator.push(ScreenRouter(AllScreens.Login))
                }) {
                    Image(
                        url = "https://cdn-icons-png.flaticon.com/512/985/985478.png",
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }



                IconButton(onClick = {
                    navigator.push(ScreenRouter(AllScreens.Login))
                }) {
                    Image(
                        url = "https://cdn.iconscout.com/icon/free/png-256/free-profile-417-1163876.png",
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

    }
}
