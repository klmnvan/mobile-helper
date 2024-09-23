package com.example.mobilehelper.domain

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilehelper.view.screens.auth.Auth
import com.example.mobilehelper.view.screens.splash.Splash

@Composable
fun Navigation() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH){
            Splash(navHostController)
        }

        composable(Routes.AUTH){
            Auth(navHostController)
        }

        composable(Routes.REGIST){
            //Splash(controller)
        }
    }
}

//Для удобного обращения к названиям экранов
object Routes {
    const val SPLASH = "splash"
    const val AUTH = "auth"
    const val REGIST = "regist"
}

