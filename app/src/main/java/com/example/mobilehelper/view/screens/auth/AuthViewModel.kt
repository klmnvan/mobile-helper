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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: String = "",
    var name: String = "",
    var surname: String = "",
    var patronymic: String = "",
    @SerialName("id_role")
    var idRole: Int = 1
)

data class State(
    val search: String = "",
    val selectedCategory: Category = Category(0, ""),
    val filmPreview: MutableList<Film> = mutableListOf(),
    val categories: MutableList<Category> = mutableListOf(),
    val categoryFilms: MutableList<FilmCategory> = mutableListOf(),
    val films: MutableList<Film> = mutableListOf(),
)

data class Film(
    val id: Int,
    val name: String
)

data class Category(
    val id: Int,
    val category: String
)

data class FilmCategory(
    val idFilm: Int,
    val idCategory: Int
)

class AuthViewModel: ViewModel() {

    var userEmail by mutableStateOf("")
    var surname by mutableStateOf("")


    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    var stateValue: State
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun init() {
        stateValue = stateValue.copy(categories = mutableListOf(Category(1, "триллер"), Category(2,"хоррор")))
        stateValue = stateValue.copy(films = mutableListOf(Film(5, "film2"), Film(6,"film1")))
        stateValue = stateValue.copy(categoryFilms = mutableListOf(FilmCategory(5, 1), FilmCategory(6,2), FilmCategory(5,2)))
        if(stateValue.categories.isNotEmpty()) {
            stateValue = stateValue.copy(selectedCategory = stateValue.categories.first())
            filter()
        }
    }

    fun filter() {
        //получаем idшники фильмов выбранной категории
        val sortedFilms = stateValue.categoryFilms.filter { it.idCategory == stateValue.selectedCategory.id }
        stateValue.filmPreview.clear()
        //перебираем список фильмов, и в список, который выводим, добавляем нужные фильмы
        val filmPreview = mutableListOf<Film>()
        sortedFilms.forEach { filmCategory ->
            val film = stateValue.films.first { it.id == filmCategory.idFilm }
            if(film.name.contains(stateValue.search, ignoreCase = true)) {
                filmPreview.add(film)
            }
        }
        stateValue = stateValue.copy(filmPreview = filmPreview)
        //stateValue = stateValue.copy(filmPreview = stateValue.filmPreview.filter { it.name.contains(stateValue.search, ignoreCase = true) }.toMutableList())
    }










    private var userPassword by mutableStateOf("123456")
    var listUser by mutableStateOf(listOf<User>())
    var role by mutableStateOf("")

    /*fun insert() {
        viewModelScope.launch {
            try {
                Constants.supabase.from("test").insert(
                    value = mapOf(Pair("name", "chto-to"), Pair("col", "chto-to"))
                )
            } catch (e: Exception) {
                Log.d("insert", e.message.toString())
            }
        }
    }

    fun signIn(context: Context) {
        viewModelScope.launch {
            try {
                Constants.supabase.auth.signInWith(Email) {
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
        listUser = Constants.supabase.from("users")
            .select().decodeSingle()
        Log.e("user", listUser.toString())
    }*/

}