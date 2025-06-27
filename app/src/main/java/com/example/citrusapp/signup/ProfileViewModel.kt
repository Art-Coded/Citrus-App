package com.example.citrusapp.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.Date

class ProfileViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    //MUTABLES
    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var gmail by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set


    //STRINGS
    fun updateFirstName(newName: String) {
        firstName = newName
    }

    fun updateLastName(newName: String) {
        lastName = newName
    }

    fun updateGmail(newGmail: String) {
        gmail = newGmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
    }


    suspend fun registerUser(email: String, password: String): Boolean {
        return try {
            val auth = Firebase.auth
            val firestore = FirebaseFirestore.getInstance()

            val methods = auth.fetchSignInMethodsForEmail(email).await()
            if (methods.signInMethods?.isNotEmpty() == true) {
                val user = auth.currentUser
                return if (user != null && user.email == email && !user.isEmailVerified) {
                    // Resend verification if same unverified user
                    user.sendEmailVerification().await()
                    true
                } else {
                    false // Email already in use by someone else
                }
            }

            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user ?: return false

            // Send email verification
            user.sendEmailVerification().await()

            // Save creation time to Firestore
            val data = hashMapOf(
                "createdAt" to FieldValue.serverTimestamp(),
                "emailVerified" to false
            )
            firestore.collection("user_metadata").document(user.uid).set(data).await()

            true
        } catch (e: Exception) {
            false
        }
    }



    fun checkEmailVerification(): Boolean {
        return auth.currentUser?.isEmailVerified ?: false
    }

    suspend fun resendVerificationEmail(): Boolean {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun loginAndCheckVerification(email: String, password: String): Pair<Boolean, String?> {
        return try {
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user ?: return Pair(false, "Login failed")

            val firestore = FirebaseFirestore.getInstance()
            val doc = firestore.collection("user_metadata").document(user.uid).get().await()
            val createdAt = doc.getTimestamp("createdAt")?.toDate()

            if (user.isEmailVerified) {
                firestore.collection("user_metadata").document(user.uid).update("emailVerified", true).await()
                return Pair(true, null)
            }

            if (createdAt != null) {
                val elapsedMillis = Date().time - createdAt.time
                val oneHourMillis = 60 * 60 * 1000

                if (elapsedMillis > oneHourMillis) {
                    user.delete().await()
                    firestore.collection("user_metadata").document(user.uid).delete().await()
                    return Pair(false, "Your verification link expired. Account deleted.")
                }
            }

            Firebase.auth.signOut()
            Pair(false, "Please verify your email first.")
        } catch (e: Exception) {
            Pair(false, "Login failed: ${e.localizedMessage}")
        }
    }

}