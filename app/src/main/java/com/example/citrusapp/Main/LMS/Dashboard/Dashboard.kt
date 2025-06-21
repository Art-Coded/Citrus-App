package com.example.citrusapp.Main.LMS.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import com.example.citrusapp.ui.theme.light_green

@Composable
fun DashboardTab(listState: LazyListState) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Welcome back, Art!",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Here's what's happening today.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            TaskOverviewCard(
                pendingTasks = 6,
                totalTasks = 10
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        itemsIndexed(List(5) {
            "Reminder: Submit Midterm Project" to "Due: Oct 15 • Mobile App Dev"
        }) { index, (title, subtitle) ->
            RecentActivityCard(
                title = title,
                subtitle = subtitle,
                showDivider = index < 4 // Show divider except for last item
            )
        }
    }
}

@Composable
fun TaskOverviewCard(
    pendingTasks: Int = 6,
    totalTasks: Int = 10
) {
    val progress = if (totalTasks > 0) pendingTasks / totalTasks.toFloat() else 0f

    val taskProgressData = listOf(
        "Mobile App Development" to 0.6f,
        "Data Structures" to 0.8f,
        "UI/UX Design" to 0.4f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Task Overview",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            // Row layout: Progress circle on the left, AI suggestion on the right
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Circular progress
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(140.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxSize(),
                        color = light_green,
                        strokeWidth = 10.dp
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Overall Progress",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                            lineHeight = 18.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Text(
                            text = "${(progress * 100).toInt()}%",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // AI suggestion
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Citrus AI Suggestion",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        text = "You’re doing great! Try finishing 2 more tasks today to stay on track.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 13.sp
                    )
                }
            }

            // Task bars
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                taskProgressData.forEach { (course, courseProgress) ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(course, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            LinearProgressIndicator(
                                progress = { courseProgress },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(8.dp),
                                color = blue_green,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("${(courseProgress * 100).toInt()}%", fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun RecentActivityCard(title: String, subtitle: String, showDivider: Boolean) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.megaphone),
                contentDescription = "Announcement Icon",
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 14.sp
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp
                )
            }
        }

        if (showDivider) {
            HorizontalDivider()
        }
    }
}
