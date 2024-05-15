package com.dar.darioapp.app.auth.aplication

import com.dar.darioapp.app.auth.adapters.ImplUserRepository
import com.dar.darioapp.app.auth.domain.IUserService
import javax.inject.Inject

class ImplUserService @Inject constructor(private val _userRepository: ImplUserRepository) :
    IUserService {


    override fun logIn(email: String, password: String) : Boolean {
        return _userRepository.logIn(email, password)
    }

    override fun signUp(email: String, password: String) : Boolean {
        return _userRepository.signUp(email, password)
    }

    override fun logOut() : Boolean {
        return _userRepository.logOut()
    }
}