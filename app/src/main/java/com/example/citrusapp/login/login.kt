package com.example.citrusapp.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.citrusapp.R
import com.example.citrusapp.signup.ProfileViewModel
import com.example.citrusapp.ui.theme.blue_green
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(homeClick: () -> Unit, onBoardingClick: () -> Unit, signupClick: () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: ProfileViewModel = viewModel()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    var hasSubmittedEmail by remember { mutableStateOf(false) }
    val isEmailValid = remember(email) {
        email.matches(Regex("^[A-Za-z0-9._%+-]+@(gmail|yahoo)\\.com$"))
    }

    // Add these state variables for validation
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var authError by remember { mutableStateOf<String?>(null) }

    val passwordFocusRequester = remember { FocusRequester() }
    var passwordVisible by remember { mutableStateOf(false) }

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
            }) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {
            IconButton(
                onClick = { onBoardingClick() },
                modifier = Modifier
                    .height(46.dp)
                    .padding(start = 16.dp, top = 18.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Login with",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(start = 34.dp, top = 16.dp)

            )
            Row {
                Image(
                    painter = painterResource(id = R.drawable.citruslogo),
                    contentDescription = "Citrus Logo",
                    modifier = Modifier
                        .height(62.dp)
                        .padding(start = 34.dp, top = 10.dp),
                    colorFilter = ColorFilter.tint(
                        if (isDarkTheme) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.schoollogo),
                    contentDescription = "School Logo",
                    modifier = Modifier
                        .height(68.dp)
                        .padding(start = 8.dp, top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = false
                        hasSubmittedEmail = false
                        authError = null
                    },
                    label = { Text("Email") },
                    isError = emailError || (hasSubmittedEmail && !isEmailValid) || authError != null,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            hasSubmittedEmail = true
                            if (email.isBlank()) {
                                emailError = true
                            } else if (!isEmailValid) {
                            } else {
                                passwordFocusRequester.requestFocus()
                            }
                        }
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Email Icon"
                        )
                    },
                    supportingText = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = when {
                                    emailError -> "Email field cannot be empty"
                                    hasSubmittedEmail && !isEmailValid -> "Please enter a valid email address"
                                    authError != null -> authError ?: " "
                                    else -> " "
                                },
                                color = if (emailError || (hasSubmittedEmail && !isEmailValid) || authError != null)
                                    MaterialTheme.colorScheme.error
                                else
                                    Color.Transparent,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    ,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = { Text("Password") },
                    isError = passwordError,
                    singleLine = true,
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .focusRequester(passwordFocusRequester),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (password.isBlank()) {
                                passwordError = true
                            } else {
                                focusManager.clearFocus()
                            }
                        }
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = "Password Icon"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                            focusManager.clearFocus() // 👉 Clear focus here too
                        }) {
                            Icon(
                                painter = painterResource(
                                    id = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                                ),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    },

                    supportingText = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = if (passwordError) "Password field cannot be empty" else " ",
                                color = if (passwordError) MaterialTheme.colorScheme.error else Color.Transparent,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.height(20.dp)
                            )
                        }
                    }
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var rememberMeChecked by remember { mutableStateOf(false) }

                    Checkbox(
                        checked = rememberMeChecked,
                        onCheckedChange = { rememberMeChecked = it }
                    )
                    Text(text = "Remember me", fontSize = 14.sp)
                }

                Text(
                    text = "Forgot Password?",
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        // TODO: Forgot password logic
                    }
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    var isValid = true
                    authError = null

                    if (email.isBlank()) {
                        emailError = true
                        isValid = false
                    } else if (!isEmailValid) {
                        hasSubmittedEmail = true
                        isValid = false
                    }

                    if (password.isBlank()) {
                        passwordError = true
                        isValid = false
                    }

                    if (isValid) {
                        isLoading = true
                        coroutineScope.launch {
                            val (success, message) = viewModel.loginAndCheckVerification(
                                email,
                                password,
                                context
                            )
                            isLoading = false
                            if (success) {
                                homeClick()
                            } else {
                                authError = when (message) {
                                    "network_error" -> "Please check your internet connection and try again" //IF theres no internet connection
                                    "account_unverified" -> "Account not verified. Please check your email" //IF the account is not verified yet
                                    "verification_expired" -> {
                                        email = "" // <--- resets user input in the outlined text fields
                                        password = "" // < --- resets user input in the outlined text fields
                                        "Verification expired. Please sign up again." // IF the unverified account got expired
                                    }
                                    "user_not_found" -> "Account doesn't exist. Please use an existing account." // IF user doesnt exist
                                    else -> "Something went wrong. Please try again later" //Something else i think
                                }

                            }
                        }
                    }
                },
                enabled = !isLoading,
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
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(text = "Login")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Don't have an account?",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Signup",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = blue_green,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable(
                        onClick = {
                            signupClick() // TODO: Navigate to Signup
                        },
                        role = Role.Button
                    )
                    .indication(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Developed by DCODE",
                fontSize = 14.sp,
                modifier = Modifier
                    .alpha(0.5f)
            )
        }
    }


}


