import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R
import com.example.citrusapp.signup.ProfileViewModel
import com.example.citrusapp.ui.theme.blue_green
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SlideOne(loginClick1: () -> Unit, viewModel: ProfileViewModel = viewModel(), onNextClick: () -> Unit) {

    val firstName = viewModel.firstName
    val lastName = viewModel.lastName

    var currentError by remember { mutableStateOf("") }

    var firstNameError by remember { mutableStateOf(false) }
    var lastNameError by remember { mutableStateOf(false) }

    val maxLength = 26
    val letterOnlyRegex = Regex("^[a-zA-Z ]*$")

    val focusManager = LocalFocusManager.current
    val lastNameFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    fun validateFields(): Boolean {
        return when {
            firstName.isBlank() -> {
                currentError = "First name field cannot be empty."
                firstNameError = true
                lastNameError = false
                false
            }
            lastName.isBlank() -> {
                currentError = "Last name field cannot be empty."
                lastNameError = true
                firstNameError = false
                false
            }
            else -> {
                currentError = ""
                firstNameError = false
                lastNameError = false
                true
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
                text = "Profile Details",
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp),
            )

            Text(
                text = "Tell us your name so others can recognize you! We’ll use your name to help create a unique and authentic identity within the app. Please ensure it's appropriate and accurate.",
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
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 16.dp)
                    .alpha(0.6f)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = {
                                if (it.length <= maxLength && it.matches(letterOnlyRegex)) {
                                    viewModel.updateFirstName(it)
                                    firstNameError = false
                                    currentError = ""
                                }
                            },
                            label = { Text("First Name") },
                            singleLine = true,
                            isError = firstNameError,
                            supportingText = {
                                Text(
                                    text = if (firstNameError) currentError else " ",
                                    color = if (firstNameError) MaterialTheme.colorScheme.error else Color.Transparent
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 0.dp),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_pencil),
                                    contentDescription = "First Name Icon"
                                )
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    lastNameFocusRequester.requestFocus()
                                }
                            )
                        )

                        Text(
                            text = "${firstName.length} / $maxLength",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 16.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }


                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = {
                                if (it.length <= maxLength && it.matches(letterOnlyRegex)) {
                                    viewModel.updateLastName(it)
                                    lastNameError = false
                                    currentError = ""
                                }
                            },
                            label = { Text("Last Name") },
                            singleLine = true,
                            isError = lastNameError,
                            supportingText = {
                                Text(
                                    text = if (lastNameError) currentError else " ",
                                    color = if (lastNameError) MaterialTheme.colorScheme.error else Color.Transparent
                                )
                            }
                            ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(lastNameFocusRequester),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_pencil),
                                    contentDescription = "Last Name Icon"
                                )
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            )
                        )

                        Text(
                            text = "${lastName.length} / $maxLength",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 16.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
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
                text = "NOTE - Using offensive or misleading names may result in a ban.",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 14.sp
                )
                Text(
                    text = "Login",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = blue_green,
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                loginClick1()// TODO: Navigate to login
                            },
                            role = Role.Button
                        )
                        .indication(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true)
                        )
                )
            }

            Button(
                onClick = {
                    if (validateFields()) {
                        onNextClick()
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
                Text(text = "Next")
            }
        }
    }
}
