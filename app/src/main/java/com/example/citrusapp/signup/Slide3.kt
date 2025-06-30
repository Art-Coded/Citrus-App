package com.example.citrusapp.signup.slides

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.citrusapp.signup.ProfileViewModel
import com.example.citrusapp.ui.theme.blue_green
import com.example.citrusapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SlideThree(loginClick1: () -> Unit, isDarkTheme: Boolean) {
    val viewModel: ProfileViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    var isVerified by remember { mutableStateOf(false) }
    var animationType by remember { mutableStateOf("loading") }
    val userEmail = FirebaseAuth.getInstance().currentUser?.email


    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun getRawResId(fileName: String): Int {
        return try {
            val resField = R.raw::class.java.getDeclaredField(fileName)
            resField.getInt(null)
        } catch (e: Exception) {
            R.raw.loading_light
        }
    }

    val resolvedFileName = when (animationType) {
        "loading" -> if (isDarkTheme) "loading_light" else "loading_dark"
        "check" -> if (isDarkTheme) "check_light" else "check_dark"
        else -> "loading_light"
    }
    val resId = getRawResId(resolvedFileName)
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    val animatable = rememberLottieAnimatable()

    LaunchedEffect(composition, animationType) {
        composition?.let {
            animatable.animate(
                composition = it,
                iterations = if (animationType == "check") 1 else LottieConstants.IterateForever
            )
        }
    }

    // 🧠 Start monitoring verification status when composable launches
    LaunchedEffect(Unit) {
        viewModel.monitorVerificationStatus {
            animationType = "check" // ✅ switch to "check" when verified
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            try {
                val isVerifiedNow = viewModel.checkVerificationAndUpdate()
                if (isVerifiedNow) {
                    isVerified = true
                    animationType = "check" // 🔁 optional: switch to check animation
                    delay(1000) // give user a moment to see the animation
                    break
                }
            } catch (e: Exception) {
                // Optionally log or show an error
                break
            }

            delay(3000)
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
                text = if (isVerified) "Account Successfully Created!" else "Gmail Verification",
                fontSize = 20.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            if (!isVerified) {
                Text(
                    text = "Verify your existing Gmail account sent to",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )

                if (!isVerified && userEmail != null) {
                    Text(
                        text = userEmail,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic
                    )
                }

            }



            //LOTTIE HERE
            LottieAnimation(
                composition = composition,
                progress = { animatable.progress },
                modifier = Modifier
                    .height(if (animationType == "check") 120.dp else 160.dp)
                    .align(Alignment.CenterHorizontally)
            )

            if (!isVerified) {
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
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = if (isVerified) "Continue back to Login page" else "If you can't see any verification mails, please try to double check your spam folder and check for any CitrusBot mail to continue",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 8.dp, end = 8.dp),
            )

            Button(
                onClick = { loginClick1() },
                enabled = isVerified,
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White,
                    disabledContainerColor = blue_green.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Text(text = if (isVerified) "Finish" else "Waiting for verification...")
            }
        }
    }
}
