package com.example.mobilehelper.view.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun Auth(navHostController: NavHostController) {

    val context = LocalContext.current
    val vm = viewModel { AuthViewModel() }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).verticalScroll(
        rememberScrollState()
    ),
        verticalArrangement = Arrangement.Center) {


        val state = vm.state.collectAsState()

        LaunchedEffect(Unit) {
            vm.init()
        }

        LaunchedEffect(state.value.selectedCategory) {
            vm.filter()
        }

        OutlinedTextField(
            value = state.value.search,
            onValueChange = {
                vm.stateValue = state.value.copy(search = it)
                vm.filter()
            },
            modifier = Modifier.fillMaxWidth(),
        )

        state.value.categories.forEach {
            Text(it.category, modifier = Modifier.background(if (state.value.selectedCategory == it) Color.Red else Color.Gray).clickable {
                vm.stateValue = state.value.copy(selectedCategory = it)
            }.padding(end = 15.dp) )
        }

        Spacer(modifier = Modifier.height(30.dp))

        state.value.filmPreview.forEach {
            Text(it.name)
        }



        /*
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp
        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.height(10.dp).width(screenHeight * 0.05f).background(Color.Gray, RoundedCornerShape(5.dp)))
            Spacer(modifier = Modifier.width(10.dp))
            Box(modifier = Modifier.height(10.dp).width(screenHeight * 0.05f).background(Color.Gray, RoundedCornerShape(5.dp)))
            Spacer(modifier = Modifier.width(10.dp))
            Box(modifier = Modifier.height(10.dp).width(screenHeight * 0.08f).background(Color.Black, RoundedCornerShape(5.dp)))
        }*//*
        Spacer(modifier = Modifier.height(15.dp))
        Spacer(modifier = Modifier.weight(1f))
        TextField(vm.userEmail, { vm.userEmail = it }, "Email")
        TextField(vm.surname, { vm.surname = it }, "Фамилия")*//*
        Button( onClick = { vm.signIn(context) } ) { Text("Авторизация") }
        Button( onClick = { vm.updateProfile(context) } ) { Text("Изменить") }
        Button( onClick = { vm.getRole(context) } ) { Text("Получить роль") }
        Button( onClick = { vm.getRole(context) } ) { Text("Получить роль") }
        Button( onClick = { vm.getRole(context) } ) { Text("Получить роль") }
        Button( onClick = { vm.insert() } ) { Text("Insert") }*/
        /*LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(vm.listUser, key = { user -> user.id } ) { user ->
                Column(modifier = Modifier.fillMaxWidth().background(Color.LightGray, RoundedCornerShape(15.dp)).padding(15.dp)) {
                    Text(user.name)
                    Text(user.surname)
                    Text(user.patronymic)
                    Text(user.idRole.toString())
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }*//*
        Spacer(modifier = Modifier.height(15.dp))
        Spacer(modifier = Modifier.weight(1f))
        Button( onClick = { vm.insert() } ) { Text("Insert") }
        Spacer(modifier = Modifier.height(15.dp))*/
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(value: String, input: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = {input(it) },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp, shape = RoundedCornerShape(30), spotColor = Color.Black
            ),
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            containerColor = Color.LightGray,
            cursorColor = Color.Black
        )
    )
}