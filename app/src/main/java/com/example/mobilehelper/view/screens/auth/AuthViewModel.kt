package com.example.mobilehelper.view.screens.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilehelper.domain.Constants
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class AuthViewModel: ViewModel() {

    var userEmail by mutableStateOf("")
    var surname by mutableStateOf("")
    private var userPassword by mutableStateOf("123456")
    var listUser by mutableStateOf(listOf<User>())
    var role by mutableStateOf("")

    fun signIn(context: Context) {
        viewModelScope.launch {
            try {
                Constants.supabase.auth.signInWith(Email){
                    this.email = userEmail
                    this.password = userPassword
                }
                getUsers()
                Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.d("signin", e.message.toString())
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getRole(context: Context) {
        viewModelScope.launch {
            try {
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                if(currentUser != null) {
                    role = Constants.supabase.from("users").select {
                        filter {
                            eq("id", currentUser.id)
                        }
                    }.decodeSingle<User>().idRole.toString()
                    Log.d("user", role)
                }
            } catch (e: Exception) {
                Log.d("signin", e.message.toString())
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateProfile(context: Context) {
        viewModelScope.launch {
            try {
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                if(currentUser != null) {
                    Log.e("user", currentUser.id)
                    Constants.supabase.from("users").update(
                        {
                            set("surname", surname)
                        }
                    ) {
                        filter {
                            eq("id", currentUser.id)
                        }
                    }
                    getUsers()
                }
            } catch (e: Exception) {
                Log.d("signin", e.message.toString())
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun getUsers() {
        listUser = Constants.supabase.from("users").select().decodeList<User>()
        Log.e("user", listUser.toString())
    }

}

@Serializable
data class User(
    var id: String = "",
    var name: String = "",
    var surname: String = "",
    var patronymic: String = "",
    @SerialName("id_role")
    var idRole: Int = 1
)