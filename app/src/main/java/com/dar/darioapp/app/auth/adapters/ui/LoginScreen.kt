package com.dar.darioapp.app.auth.adapters.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dar.darioapp.R
import com.dar.darioapp.app.auth.adapters.AuthViewModel
import com.dar.darioapp.app.auth.domain.User
import com.dar.darioapp.app.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Login(navController, Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(navController: NavHostController, modifier: Modifier, viewModel: AuthViewModel) {
    val user: User by viewModel.user.observeAsState(initial = User("", ""))
    val loginEnable: Boolean by viewModel.areFieldsValid.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(user.email) { viewModel.onLoginChange(it, user.password) }
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(user.password) { viewModel.onLoginChange(user.email, it) }
            Spacer(modifier = Modifier.padding(20.dp))
            LoginButton(
                Modifier.align(Alignment.CenterHorizontally),
                loginEnable
            ) {navController.navigate(AppScreens.MoviesListScreen.route)
                coroutineScope.launch { viewModel.onLoginSelected() } }

            Spacer(modifier = Modifier.padding(12.dp))

            SignUpButton(
                Modifier.align(Alignment.CenterHorizontally),
                loginEnable
            ) { coroutineScope.launch { viewModel.onSignUpSelected() } }
        }
    }

}

@Composable
fun SignUpButton(modifier: Modifier, loginEnable: Boolean, onSignUpSelected: () -> Unit) {
    Button(
        onClick = { onSignUpSelected() },
        modifier = modifier
            .height(50.dp)
            .width(200.dp),
        enabled = loginEnable,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Color(0xFFC9C3FF),
            contentColor = Color(0xFF44454E),
            containerColor = Color(0xFF5948FF)
        )
    ) {
        Text(text = "Signup")
    }
}

@Composable
fun LoginButton(modifier: Modifier, loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = {
            onLoginSelected()            },
        modifier = modifier
            .height(50.dp)
            .width(200.dp),
        enabled = loginEnable,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Color(0xFFEDEBFD),
            contentColor = Color(0xFF44454E),
            containerColor = Color(0xFFA9A1F8)
        )
    ) {
        Text(text = "Login")
    }
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedContainerColor = Color(0xFFE4E4E4),
            unfocusedContainerColor = Color(0xFF7E7E7E),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}


@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {
            onTextFieldChanged(it)
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedContainerColor = Color(0xFFE4E4E4),
            unfocusedContainerColor = Color(0xFF7E7E7E),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logoapp),
        contentDescription = "loginHeader",
        modifier = modifier
    )
}
