package com.dar.darioapp.app.auth.aplication

interface IUserRepository {
    fun logIn(email: String, password: String): Boolean
    fun signUp(email: String, password: String): Boolean
    fun logOut(): Boolean
}