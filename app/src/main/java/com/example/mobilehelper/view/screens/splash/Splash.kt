package com.example.mobilehelper.view.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.example.mobilehelper.domain.Routes
import kotlinx.coroutines.delay

@Composable
fun Splash(navHostController: NavHostController) {

    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            delay(1500L)
            navHostController.navigate(Routes.AUTH) {
                popUpTo(Routes.SPLASH) {
                    inclusive = true
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Splash", color = Color.Black)
    }

}