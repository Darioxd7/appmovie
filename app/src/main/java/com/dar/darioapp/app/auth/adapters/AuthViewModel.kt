package com.dar.darioapp.app.auth.adapters

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dar.darioapp.app.auth.aplication.ImplUserService
import com.dar.darioapp.app.auth.domain.IUserService
import com.dar.darioapp.app.auth.domain.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val _userService: ImplUserService) : ViewModel() {

    private val _user = MutableLiveData<User>()
    private val _areFieldsValid = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    val user: LiveData<User> = _user
    val isLoading: LiveData<Boolean> = _isLoading
    val areFieldsValid: LiveData<Boolean> = _areFieldsValid


    fun onLoginChange(email: String, password: String) {
        val currentUser = _user.value ?: User("", "")
        val updatedUser = currentUser.copy(email = email, password = password)
        _user.value = updatedUser
        _areFieldsValid.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.count() > 6

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()


    fun onLoginSelected() {
        _isLoading.value = true
        _userService.logIn(_user.value?.email ?: "", _user.value?.password ?: "")
        _isLoading.value = false
    }

    fun onSignUpSelected() {
        _isLoading.value = true
        _userService.signUp(_user.value?.email ?: "", _user.value?.password ?: "")
        _isLoading.value = false
    }
}