package com.dar.darioapp.app.auth.domain

import com.dar.darioapp.app.auth.aplication.IUserRepository

interface IUserService {
    fun logIn(email: String, password: String): Boolean
    fun signUp(email: String, password: String): Boolean
    fun logOut(): Boolean
}