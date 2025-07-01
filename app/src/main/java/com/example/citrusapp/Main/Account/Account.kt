package com.example.citrusapp.Main.Account

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavController? = null, rootNavController: NavHostController? = null) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var showBottomSheetLogout by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.sad_emote2)
    )

    if (showBottomSheetLogout) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheetLogout = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Please don't go ):",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = "Are you sure you want to logout?",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {

                            FirebaseAuth.getInstance().signOut()
                            Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
                            rootNavController?.navigate("login") {
                                popUpTo("BottomNav") { inclusive = true }
                            }
                            showBottomSheetLogout = false
                        }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Logout",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {
                            showBottomSheetLogout = false
                        }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Art Lyndone Acuesta Hemplo",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Lyndonehemplo1@gmail.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }


        Text(
            text = "Account",
            modifier = Modifier.padding(start = 32.dp ,top = 16.dp),
            fontSize = 13.sp
        )
        Surface(
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SettingsItem(title = "Account", iconResId = R.drawable.ic_account, onClick = {navController?.navigate("accountEdit")})
                SettingsItem(title = "Privacy and Security", iconResId = R.drawable.ic_security, onClick = {navController?.navigate("privacy")})
            }
        }

        Text(
            text = "Display",
            modifier = Modifier.padding(start = 32.dp ,top = 16.dp),
            fontSize = 13.sp
        )
        Surface(
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SettingsItem(title = "Notification", iconResId = R.drawable.ic_notification, onClick = {navController?.navigate("notification")})
                SettingsItem(title = "Display", iconResId = R.drawable.ic_display, onClick = {  })
            }
        }


        Text(
            text = "Support & About",
            modifier = Modifier.padding(start = 32.dp ,top = 16.dp),
            fontSize = 13.sp
        )
        Surface(
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SettingsItem(title = "Report a problem", iconResId = R.drawable.ic_report, onClick = {navController?.navigate("report")})
                SettingsItem(title = "Support", iconResId = R.drawable.ic_support, onClick = {navController?.navigate("support")})
                SettingsItem(title = "Terms and Policies", iconResId = R.drawable.ic_terms, onClick = {navController?.navigate("terms")})
            }
        }

        Text(
            text = "Support Us \uD83E\uDDE1",
            modifier = Modifier.padding(start = 32.dp ,top = 16.dp),
            fontSize = 13.sp
        )
        Surface(
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SettingsItem(title = "Contribute", iconResId = R.drawable.ic_smile, onClick = {navController?.navigate("contribute")})
            }
        }

        Text(
            text = "Log out",
            modifier = Modifier.padding(start = 34.dp ,top = 16.dp),
            fontSize = 13.sp
        )
        Surface(
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
            modifier = Modifier.fillMaxWidth().padding(bottom = 36.dp, start = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SettingsItem(title = "Log out Account", iconResId = R.drawable.ic_logout, onClick = {scope.launch { showBottomSheetLogout = true } })
            }
        }

        Text(
            text = "Version 1.0.0",
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SettingsItem(
    title: String,
    iconResId: Int,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier.size(24.dp).alpha(0.8f),
                tint = blue_green
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "Go to $title",
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun SettingsOtherItem(
    title: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "Go to $title",
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
