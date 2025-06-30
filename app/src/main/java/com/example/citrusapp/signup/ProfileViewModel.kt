package com.example.citrusapp.signup

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citrusapp.login.NetworkUtils
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

            // Store user data including names
            firestore.collection("user_metadata").document(user.uid).set(
                hashMapOf(
                    "firstName" to firstName,
                    "lastName" to lastName,
                    "email" to email,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "emailVerified" to false
                )
            ).await()

            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun monitorVerificationStatus(onVerified: () -> Unit) {
        val user = auth.currentUser

        // ✅ SAFEGUARD: Make sure the user is valid before checking
        if (user == null || user.email.isNullOrEmpty()) {
            return
        }

        repeat(12) {
            try {
                user.reload().await()
                if (user.isEmailVerified) {
                    // Update Firestore
                    FirebaseFirestore.getInstance()
                        .collection("user_metadata")
                        .document(user.uid)
                        .update("emailVerified", true)
                        .await()
                    onVerified()
                    return
                }
                delay(5000)
            } catch (e: FirebaseAuthInvalidUserException) {
                // User might’ve been deleted before reload
                return
            } catch (e: Exception) {
                // Some other error happened
                return
            }
        }
    }


    suspend fun checkVerificationAndUpdate(): Boolean {
        return try {
            val user = auth.currentUser
            user?.reload()?.await() // Force refresh

            if (user?.isEmailVerified == true) {
                // Update Firestore
                FirebaseFirestore.getInstance()
                    .collection("user_metadata")
                    .document(user.uid)
                    .update("emailVerified", true)
                    .await()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun resendVerificationEmail(): Boolean {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun loginAndCheckVerification(
        email: String,
        password: String,
        context: Context
    ): Pair<Boolean, String?> {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            return Pair(false, "network_error")
        }

        return try {
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user ?: return Pair(false, "login_failed")

            val firestore = FirebaseFirestore.getInstance()
            val doc = firestore.collection("user_metadata").document(user.uid).get().await()
            val createdAt = doc.getTimestamp("createdAt")?.toDate()

            if (user.isEmailVerified) {
                // Update Firestore with verification status while preserving other fields
                firestore.collection("user_metadata").document(user.uid).update(
                    mapOf(
                        "emailVerified" to true,
                        "lastLogin" to FieldValue.serverTimestamp() // Optional: add login timestamp
                    )
                ).await()
                return Pair(true, null)
            }

            if (createdAt != null) {
                val elapsedMillis = Date().time - createdAt.time
                val oneHourMillis = 60 * 60 * 1000 // 1 minute for testing (change to 60 * 60 * 1000 for production)

                if (elapsedMillis > oneHourMillis) {
                    // Delete both Firestore document AND auth account
                    firestore.collection("user_metadata").document(user.uid).delete().await()
                    user.delete().await() // This deletes the auth account
                    return Pair(false, "verification_expired")
                }
            }

            Pair(false, "account_unverified")
        } catch (e: FirebaseAuthInvalidUserException) {
            return Pair(false, "user_not_found")
        } catch (e: FirebaseNetworkException) {
            return Pair(false, "network_error")
        } catch (e: Exception) {
            return Pair(false, "login_failed")
        }

    }

}