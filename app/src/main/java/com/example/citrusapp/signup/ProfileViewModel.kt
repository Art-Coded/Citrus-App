package com.example.citrusapp.signup

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.citrusapp.login.NetworkUtils
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel() {

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

    suspend fun registerUser(email: String, password: String): Pair<Boolean, String?> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user ?: return Pair(false, "Registration failed. Please try again.")

            user.sendEmailVerification().await()

            firestore.collection("user_metadata").document(user.uid).set(
                hashMapOf(
                    "firstName" to firstName,
                    "lastName" to lastName,
                    "email" to email,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "emailVerified" to false
                )
            ).await()

            Pair(true, "Verification email sent. Please check your inbox.")
        } catch (e: FirebaseAuthUserCollisionException) {
            Pair(false, "This email is already in use by another account.")
        } catch (e: FirebaseNetworkException) {
            Pair(false, "Network error. Please check your connection.")
        } catch (e: Exception) {
            Pair(false, "Registration failed. ${e.message}")
        }
    }

    suspend fun monitorVerificationStatus(onVerified: () -> Unit) {
        val user = auth.currentUser ?: return

        if (user.email.isNullOrEmpty()) {
            return
        }

        repeat(12) {
            try {
                user.reload().await()
                if (user.isEmailVerified) {
                    firestore.collection("user_metadata")
                        .document(user.uid)
                        .update("emailVerified", true)
                        .await()
                    onVerified()
                    return
                }
                delay(5000)
            } catch (e: FirebaseAuthInvalidUserException) {
                return
            } catch (e: Exception) {
                return
            }
        }
    }

    suspend fun checkVerificationAndUpdate(): Boolean {
        return try {
            val user = auth.currentUser
            user?.reload()?.await()

            if (user?.isEmailVerified == true) {
                firestore.collection("user_metadata")
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

    suspend fun loginAndCheckVerification(email: String, password: String, context: Context): Pair<Boolean, String?> {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            return Pair(false, "network_error")
        }

        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user ?: return Pair(false, "login_failed")

            val doc = firestore.collection("user_metadata").document(user.uid).get().await()
            val createdAt = doc.getTimestamp("createdAt")?.toDate()

            if (user.isEmailVerified) {
                firestore.collection("user_metadata").document(user.uid).update(
                    mapOf(
                        "emailVerified" to true,
                        "lastLogin" to FieldValue.serverTimestamp()
                    )
                ).await()
                return Pair(true, null)
            }

            if (createdAt != null) {
                val elapsedMillis = Date().time - createdAt.time
                val oneHourMillis = 60 * 60 * 1000 // 1 hour

                if (elapsedMillis > oneHourMillis) {
                    firestore.collection("user_metadata").document(user.uid).delete().await()
                    user.delete().await()
                    return Pair(false, "verification_expired")
                }
            }

            Pair(false, "account_unverified")
        } catch (e: FirebaseNetworkException) {
            return Pair(false, "network_error")
        } catch (e: Exception) {
            return Pair(false, "login_failed")
        }
    }

    fun reset() {
        firstName = ""
        lastName = ""
        gmail = ""
        password = ""
        confirmPassword = ""
    }


    private var isProfileFetched by mutableStateOf(false)
    private var isFetching by mutableStateOf(false)

    suspend fun fetchUserProfile(forceRefresh: Boolean = false): Boolean {
        // If already fetched and not forcing refresh, return cached data
        if (isProfileFetched && !forceRefresh) {
            return true
        }

        // If already fetching, wait
        if (isFetching) {
            return false
        }

        isFetching = true
        return try {
            val user = auth.currentUser ?: return false.also { isFetching = false }
            val snapshot = firestore.collection("user_metadata")
                .document(user.uid)
                .get()
                .await()

            snapshot.getString("firstName")?.let { firstName = it }
            snapshot.getString("lastName")?.let { lastName = it }
            snapshot.getString("email")?.let { gmail = it }

            isProfileFetched = true
            true
        } catch (e: Exception) {
            false
        } finally {
            isFetching = false
        }
    }


}
