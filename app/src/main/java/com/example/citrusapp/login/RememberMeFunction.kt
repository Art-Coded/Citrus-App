package com.example.citrusapp.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class RememberMeFunction(private val context: Context) {
    companion object {
        val REMEMBER_ME = booleanPreferencesKey("remember_me")
        val EMAIL = stringPreferencesKey("email")
    }

    suspend fun saveCredentials(email: String, rememberMe: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[REMEMBER_ME] = rememberMe
            if (rememberMe) {
                prefs[EMAIL] = email
            } else {
                prefs.remove(EMAIL)
            }
        }
    }

    suspend fun getRememberedEmail(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[EMAIL]
    }

    suspend fun isRemembered(): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[REMEMBER_ME] ?: false
    }
}
