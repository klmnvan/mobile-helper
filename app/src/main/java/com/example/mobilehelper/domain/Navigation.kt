package com.example.mobilehelper.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(controller: NavHostController, visibleBBar: MutableState<Boolean>) {
    NavHost(
        navController = controller,
        startDestination = "start") {

        /*composable(NavigationRoutes.SPLASH){
            Splash(controller)
        }
        composable(NavigationRoutes.AUTH){
            visibleBBar.value = false
            Auth(controller)
        }
        composable(NavigationRoutes.REGIST){
            Regist(controller)
        }
        composable(NavigationRoutes.HOME) {
            visibleBBar.value = true
            if(General.role == "Админ") HomeAdmin(controller)
            else Home(controller)
        }
        composable(NavigationRoutes.PROFILE) {
            visibleBBar.value = true
            Profile(controller)
        }
        composable(NavigationRoutes.CREATEORDER) {
            CreateOrder(controller)
        }*/
    }
}

