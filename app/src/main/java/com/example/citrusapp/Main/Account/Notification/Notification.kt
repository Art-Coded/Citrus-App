package com.example.citrusapp.Main.Account.Notification

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun NotificationScreen(navController: NavController) {

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
                text = "Notification",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
        }

        Text(
            text = "General",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
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
                SettingsOtherItem(title = "On", onClick = { })
            }
        }

        Text(
            text = "Notification preferences",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
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
                SettingsOtherItem(title = "Lost and Found", onClick = { })
                SettingsOtherItem(title = "LMS", onClick = { })
                SettingsOtherItem(title = "Calendar Schedules", onClick = { })
                SettingsOtherItem(title = "Network", onClick = { })
                SettingsOtherItem(title = "Incoming Messages", onClick = { })
                SettingsOtherItem(title = "Events", onClick = { })
            }
        }
    }
}