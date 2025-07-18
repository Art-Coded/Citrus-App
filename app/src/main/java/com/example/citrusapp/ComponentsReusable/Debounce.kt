package com.example.citrusapp.ComponentsReusable

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DebounceHelper(private val delayMillis: Long = 300L) {
    private var lastCallTime = 0L
    private var currentJob: Job? = null

    suspend fun <T> debounce(
        action: suspend () -> T
    ): T? {
        currentJob?.cancel()

        val now = System.currentTimeMillis()
        if (now - lastCallTime < delayMillis) {
            delay(delayMillis - (now - lastCallTime))
        }

        return try {
            currentJob = CoroutineScope(Dispatchers.IO).launch {
                lastCallTime = System.currentTimeMillis()
            }
            action()
        } finally {
            currentJob = null
        }
    }
}