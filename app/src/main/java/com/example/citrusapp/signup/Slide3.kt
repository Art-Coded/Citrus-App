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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.citrusapp.ui.theme.blue_green

@Composable
fun SlideThree() {
    val otpValues = remember { List(6) { mutableStateOf("") } }
    val focusRequesters = remember { List(6) { FocusRequester() } }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                text = "Almost there! We've sent a Verification code to your email. Enter it below to activate your account.",
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
                text = "OTP Verification",
                fontSize = 24.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            Text(
                text = "Enter the OTP sent to",
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

            // Enhanced OTP Input Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                otpValues.forEachIndexed { index, state ->
                    OutlinedTextField(
                        value = state.value,
                        onValueChange = { newValue ->
                            when {
                                // Handle backspace/empty input
                                newValue.isEmpty() -> {
                                    state.value = ""
                                    if (index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                                // Handle digit input
                                newValue.length == 1 && newValue.all { char -> char.isDigit() } -> {
                                    state.value = newValue
                                    if (index < 5) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else {
                                        focusManager.clearFocus()
                                    }
                                }
                                // Handle paste (take first 6 digits)
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

            // Resend OTP Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Didn't receive the OTP?",
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Resend OTP",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = blue_green,
                    modifier = Modifier
                        .clickable {
                            // TODO: Handle resend OTP logic here
                        }
                        .padding(4.dp)
                )
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
                text = "You need to confirm your email address within 24 hours, or your account will be deleted.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 8.dp, end = 8.dp),
            )
            Button(
                onClick = {
                    // TODO: Back to login
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
                Text(text = "Finish")
            }
        }
    }
}
