package com.example.citrusapp.signup.slides

import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.citrusapp.R
import com.example.citrusapp.signup.ProfileViewModel
import com.example.citrusapp.ui.theme.blue_green
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun SlideTwo(viewModel: ProfileViewModel = viewModel(), onNextClick: () -> Unit) {
    val gmail = viewModel.gmail
    val password = viewModel.password
    val confirmPassword = viewModel.confirmPassword

    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var showGmailError by remember { mutableStateOf(false) }
    val strictEmailRegex = Regex("^[A-Za-z0-9+_.-]+@(gmail\\.com|yahoo\\.com)$")

    val showConfirmPasswordError = remember(password, confirmPassword) {
        confirmPassword.isNotEmpty() && confirmPassword != password
    }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var registrationSuccess by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            errorMessage = null
        }
    }

    // In SlideTwo composable
    if (registrationSuccess) {
        LaunchedEffect(Unit) {
            viewModel.monitorVerificationStatus {
                // This will be called when verification is detected
                // You can show a message or navigate automatically
                onNextClick()
            }
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
                text = "Secure Your Account",
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                lineHeight = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
            Text(
                text = "We are committed to protecting your data. Your credentials are securely stored and never shared with third parties.",
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp)
                    .alpha(0.6f)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    OutlinedTextField(
                        value = gmail,
                        onValueChange = {
                            viewModel.updateGmail(it)
                            showGmailError = false
                            emailError = false
                        },
                        label = { Text("Email") },
                        isError = showGmailError || emailError,
                        supportingText = {
                            Text(
                                text = when {
                                    emailError -> "Email field cannot be empty"
                                    showGmailError -> "Please enter a valid email address"
                                    else -> " "
                                },
                                color = if (showGmailError || emailError) MaterialTheme.colorScheme.error else Color.Transparent,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.height(20.dp)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_email),
                                contentDescription = "Email Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (gmail.isBlank()) {
                                    emailError = true
                                } else if (!strictEmailRegex.matches(gmail)) {
                                    showGmailError = true
                                } else {
                                    passwordFocusRequester.requestFocus()
                                }
                            }
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))


                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            viewModel.updatePassword(it)
                            passwordError = false
                        },
                        label = { Text("Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(passwordFocusRequester),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "Password Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (password.isBlank()) {
                                    passwordError = true
                                } else {
                                    confirmPasswordFocusRequester.requestFocus()
                                }
                            }
                        ),
                        supportingText = {
                            when {
                                passwordError -> Text(
                                    text = "Password field cannot be empty",
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.height(20.dp)
                                )
                                password.isNotEmpty() -> Text(
                                    text = buildAnnotatedString {
                                        append("Password strength: ")
                                        withStyle(style = SpanStyle(color = getPasswordStrength(password).second)) {
                                            append(getPasswordStrength(password).first)
                                        }
                                    },
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.height(20.dp)
                                )
                                else -> Text(
                                    text = " ",
                                    color = Color.Transparent,
                                    modifier = Modifier.height(20.dp)
                                )
                            }
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisible = !passwordVisible
                                focusManager.clearFocus()
                            }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                                    ),
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            viewModel.updateConfirmPassword(it)
                            confirmPasswordError = false
                        },
                        label = { Text("Confirm Password") },
                        singleLine = true,
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(confirmPasswordFocusRequester),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "Password Icon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (confirmPassword.isBlank()) {
                                    confirmPasswordError = true
                                } else if (password != confirmPassword) {
                                    confirmPasswordError = true
                                } else {
                                    focusManager.clearFocus()
                                }
                            }
                        ),
                        isError = confirmPasswordError || showConfirmPasswordError,
                        supportingText = {
                            Text(
                                text = when {
                                    confirmPasswordError -> "Confirm password field cannot be empty"
                                    showConfirmPasswordError -> "Passwords do not match"
                                    else -> " "
                                },
                                color = if (confirmPasswordError || showConfirmPasswordError)
                                    MaterialTheme.colorScheme.error
                                else Color.Transparent,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.height(20.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                confirmPasswordVisible = !confirmPasswordVisible
                                focusManager.clearFocus()
                            }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (confirmPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                                    ),
                                    contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                                )
                            }
                        }
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
            val context = LocalContext.current
            val defaultTextColor = MaterialTheme.colorScheme.onBackground

            val annotatedText = buildAnnotatedString {
                append("By signing up, you agree to our ")

                pushStringAnnotation(tag = "URL", annotation = "https://youtu.be/dQw4w9WgXcQ?si=1NVPIUDQlT5gn_cX")
                withStyle(style = SpanStyle(color = blue_green, textDecoration = TextDecoration.Underline)) {
                    append("Terms of Service")
                }
                pop()

                append(" and ")

                pushStringAnnotation(tag = "URL", annotation = "https://youtu.be/dQw4w9WgXcQ?si=1NVPIUDQlT5gn_cX")
                withStyle(style = SpanStyle(color = blue_green, textDecoration = TextDecoration.Underline)) {
                    append("Privacy Policy")
                }
                pop()
            }

            var layoutResult: TextLayoutResult? = null

            Text(
                text = annotatedText,
                style = TextStyle(fontSize = 14.sp, color = defaultTextColor, textAlign = TextAlign.Center),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures { tapOffset ->
                            layoutResult?.let { layout ->
                                val position = layout.getOffsetForPosition(tapOffset)
                                annotatedText
                                    .getStringAnnotations("URL", position, position)
                                    .firstOrNull()?.let { annotation ->
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                        context.startActivity(intent)
                                    }
                            }
                        }
                    },
                onTextLayout = { result ->
                    layoutResult = result
                }
            )

            Button(
                onClick = {
                    val emailEmpty = gmail.isBlank()
                    val passwordEmpty = password.isBlank()
                    val confirmPasswordEmpty = confirmPassword.isBlank()
                    val emailInvalid = !emailEmpty && !strictEmailRegex.matches(gmail)

                    emailError = emailEmpty
                    passwordError = passwordEmpty
                    confirmPasswordError = confirmPasswordEmpty
                    showGmailError = emailInvalid

                    if (!emailEmpty && !emailInvalid && !passwordEmpty && !confirmPasswordEmpty) {
                        if (password == confirmPassword) {
                            isLoading = true
                            errorMessage = null

                            coroutineScope.launch {
                                val (success, message) = viewModel.registerUser(gmail, password)
                                isLoading = false
                                message?.let {
                                    errorMessage = it
                                    if (success) {
                                        onNextClick()
                                    }
                                }
                            }
                        } else {
                            confirmPasswordError = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                } else {
                    Text(text = "Next")
                }
            }


        }
    }
}

fun getPasswordStrength(password: String): Triple<String, Color, Float> {
    val length = password.length
    val letterCount = password.count { it.isLetter() }
    val digitCount = password.count { it.isDigit() }
    val symbolCount = password.count { !it.isLetterOrDigit() }

    val strong = (letterCount >= 10 && digitCount == 0 && symbolCount == 0) ||
            (letterCount >= 8 && digitCount >= 2) ||
            (letterCount >= 9 && digitCount >= 1) ||
            (letterCount >= 5 && digitCount >= 2 && symbolCount >= 1) ||
            (length > 9)

    val medium = !strong && length >= 8 && letterCount >= 5 && (digitCount >= 1 || symbolCount >= 1)

    return when {
        strong -> Triple("Strong", Color(0xFF2E7D32), 1f)      // Full green bar
        medium -> Triple("Medium", Color(0xFFF9A825), 0.6f)   // Yellow bar (60%)
        else -> Triple("Weak", Color.Red, 0.3f)               // Red bar (30%)
    }
}

