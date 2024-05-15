package com.dar.darioapp.app.auth.adapters

import com.dar.darioapp.app.auth.aplication.IUserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ImplUserRepository @Inject constructor(private val _firebaseAuth: FirebaseAuth) :
    IUserRepository {

    override fun logIn(email: String, password: String): Boolean {
        return try {
            _firebaseAuth.signInWithEmailAndPassword(email, password).isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override fun signUp(email: String, password: String): Boolean {
        return try {
            _firebaseAuth.createUserWithEmailAndPassword(email, password).isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override fun logOut(): Boolean {
        return try {
            _firebaseAuth.signOut()
            true
        } catch (e: Exception) {
            false
        }
    }
}