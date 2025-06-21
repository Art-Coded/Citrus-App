package com.example.citrusapp.Main.Account.AccountEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citrusapp.Main.Account.SettingsItem
import com.example.citrusapp.Main.Account.SettingsOtherItem
import com.example.citrusapp.R

@Composable
fun AccountEditScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Account",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
        }

        Text(
            text = "Account Information",
            modifier = Modifier.padding(start = 16.dp ,top = 16.dp),
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
                SettingsItem(title = "Email", iconResId = R.drawable.ic_account, onClick = {})
                SettingsItem(title = "Password", iconResId = R.drawable.ic_privacy, onClick = { })
                SettingsItem(title = "First Name", iconResId = R.drawable.ic_pencil, onClick = { })
                SettingsItem(title = "Last Name", iconResId = R.drawable.ic_pencil, onClick = { })
            }
        }

        Text(
            text = "Enhance your Profile (Optional)",
            modifier = Modifier.padding(start = 16.dp ,top = 16.dp),
            fontSize = 13.sp
        )
        Text(
            text = "Enhancing your profile would make you feel more engaging when searching for Jobs, search for Jobs now! Here",
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp).alpha(0.8f),
            fontSize = 11.sp,
            lineHeight = 14.sp
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
                SettingsOtherItem(title = "Headline", onClick = { })
                SettingsOtherItem(title = "Skills", onClick = { })
                SettingsOtherItem(title = "Pronouns", onClick = { })
                SettingsOtherItem(title = "Contact Info", onClick = { })
                SettingsOtherItem(title = "School", onClick = { })
                SettingsOtherItem(title = "Location", onClick = { })
            }
        }
    }
}