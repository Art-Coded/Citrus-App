package com.example.citrusapp.signup.slides

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.citrusapp.signup.ProfileViewModel
import com.example.citrusapp.ui.theme.blue_green
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SlideThree(loginClick1: () -> Unit) {
    val viewModel: ProfileViewModel = viewModel()

    val otpValues = remember { List(6) { mutableStateOf("") } }
    val focusRequesters = remember { List(6) { FocusRequester() } }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var isLoading by remember { mutableStateOf(false) }
    var isVerified by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (!isVerified) {
            delay(5000)
            isVerified = viewModel.checkEmailVerification()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
        ) {
            Text(
                text = "Verify Your Email",
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
            Text(
                text = "Almost there! We've sent a Verification code to your email. Kindly check your Gmail to activate your account.",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)

            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Gmail Verification",
                fontSize = 24.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            Text(
                text = "Verify your existing Gmail account sent to",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            Text(
                text = "Art@gmail.com",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(bottom = 40.dp)
            )


            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                otpValues.forEachIndexed { index, state ->
                    OutlinedTextField(
                        value = state.value,
                        onValueChange = { newValue ->
                            when {

                                newValue.isEmpty() -> {
                                    state.value = ""
                                    if (index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }

                                newValue.length == 1 && newValue.all { char -> char.isDigit() } -> {
                                    state.value = newValue
                                    if (index < 5) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else {
                                        focusManager.clearFocus()
                                    }
                                }

                                newValue.length > 1 -> {
                                    val digits = newValue.take(6).filter { it.isDigit() }
                                    digits.forEachIndexed { i, c ->
                                        if (i < 6) {
                                            otpValues[i].value = c.toString()
                                        }
                                    }
                                    if (digits.length == 6) {
                                        focusManager.clearFocus()
                                    } else if (digits.isNotEmpty()) {
                                        val lastIndex = digits.length - 1
                                        if (lastIndex < 5) {
                                            focusRequesters[lastIndex + 1].requestFocus()
                                        }
                                    }
                                }
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = if (index == 5) ImeAction.Done else ImeAction.Next
                        ),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .width(48.dp)
                            .height(56.dp)
                            .focusRequester(focusRequesters[index])
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused && state.value.isEmpty()) {
                                    // Clear previous fields when moving back
                                    for (i in index + 1 until 6) {
                                        if (otpValues[i].value.isNotEmpty()) {
                                            otpValues[i].value = ""
                                        }
                                    }
                                }
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Didn't receive the Verification?",
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))

                var cooldownSeconds by remember { mutableStateOf(0) }
                var resendFailCount by remember { mutableStateOf(0) }
                val maxResendAttempts = 3

                if (cooldownSeconds > 0) {
                    Text(
                        text = "Resend in ${cooldownSeconds}s",
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(4.dp)
                    )

                    LaunchedEffect(cooldownSeconds) {
                        if (cooldownSeconds > 0) {
                            delay(1000)
                            cooldownSeconds--
                        }
                    }
                } else if (resendFailCount >= maxResendAttempts) {
                    Text(
                        text = "Too many attempts",
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(4.dp)
                    )
                } else {
                    Text(
                        text = "Resend",
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = blue_green,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    cooldownSeconds = 30 // Start cooldown immediately
                                    isLoading = true
                                    val success = viewModel.resendVerificationEmail()
                                    isLoading = false
                                    if (!success) {
                                        resendFailCount++
                                        errorMessage = "Failed to resend verification email"
                                    }
                                }
                            }
                            .padding(4.dp)
                    )
                }

            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Didn't receive any verification? Please double check your spam folder and check for any CitrusBot mail to continue",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 8.dp, end = 8.dp),
            )
            Button(
                onClick = {
                    if (isVerified) {
                        loginClick1() //Back to login screen
                    } else {
                        errorMessage = "Please verify your email first"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Text(text = if (isVerified) "Finish" else "Waiting for Verification...")
            }
        }
    }
}
