package com.example.citrusapp.Main.Home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Composable
fun Calendar() {
    val isDarkTheme = isSystemInDarkTheme()
    var selectedMonth by remember { mutableStateOf(YearMonth.now()) }
    var direction by remember { mutableStateOf(0) }
    var isExpanded by remember { mutableStateOf(false) }
    val today = LocalDate.now()

    var dragDistance by remember { mutableStateOf(0f) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false // must be false if you're clipping later
            )
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (dragDistance > 100f) {
                            direction = -1
                            selectedMonth = selectedMonth.minusMonths(1)
                        } else if (dragDistance < -100f) {
                            direction = 1
                            selectedMonth = selectedMonth.plusMonths(1)
                        }
                        dragDistance = 0f
                    },
                    onHorizontalDrag = { _, delta ->
                        dragDistance += delta
                    }
                )
            }
    )

    {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header with navigation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "<",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .clickable {
                            direction = -1
                            selectedMonth = selectedMonth.minusMonths(1)
                        }
                        .padding(8.dp)
                )

                // Animated month/year display
                AnimatedContent(
                    targetState = selectedMonth,
                    transitionSpec = {
                        if (direction > 0) {
                            (slideInHorizontally { width -> width } + fadeIn())
                                .togetherWith(slideOutHorizontally { width -> -width } + fadeOut())
                        } else {
                            (slideInHorizontally { width -> -width } + fadeIn())
                                .togetherWith(slideOutHorizontally { width -> width } + fadeOut())
                        }.using(
                            SizeTransform(clip = false)
                        )
                    },
                    label = "MonthYearAnimation"
                ) { targetMonth ->
                    Text(
                        text = "${targetMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${targetMonth.year}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Text(
                    text = ">",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .clickable {
                            direction = 1
                            selectedMonth = selectedMonth.plusMonths(1)
                        }
                        .padding(8.dp)
                )
            }

            // Day headers
            Row(modifier = Modifier.fillMaxWidth()) {
                listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Animated calendar days
            AnimatedContent(
                targetState = selectedMonth,
                transitionSpec = {
                    if (direction > 0) {
                        (slideInHorizontally { width -> width } + fadeIn())
                            .togetherWith(slideOutHorizontally { width -> -width } + fadeOut())
                    } else {
                        (slideInHorizontally { width -> -width } + fadeIn())
                            .togetherWith(slideOutHorizontally { width -> width } + fadeOut())
                    }.using(
                        SizeTransform(clip = false)
                    )
                },
                label = "CalendarAnimation"
            ) { targetMonth ->
                CalendarGrid(targetMonth, today)
            }

            // Animated expand/collapse button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable { isExpanded = !isExpanded },
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Image(
                        painter = painterResource(
                            id = if (isExpanded) R.drawable.ic_arrow_up
                            else R.drawable.ic_arrow_down
                        ),
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(
                            if (isDarkTheme) MaterialTheme.colorScheme.onBackground
                            else MaterialTheme.colorScheme.onBackground
                        )
                    )

                    Text(
                        text = if (isExpanded) "Collapse" else "Expand",
                        modifier = Modifier.padding(start = 2.dp).alpha(0.7f),
                        fontSize = 11.sp
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top,
                    animationSpec = tween(durationMillis = 300)
                ) + fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.schoollogo),
                            contentDescription = "No Events",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(bottom = 8.dp),
                            alpha = 0.6f
                        )
                        Text(
                            text = "No scheduled events",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Check back later!",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarGrid(month: YearMonth, today: LocalDate) {
    val daysInMonth = month.lengthOfMonth()
    val firstDayOfWeek = month.atDay(1).dayOfWeek.value % 7
    var day = 1

    Column {
        val totalCells = daysInMonth + firstDayOfWeek
        val weeks = (totalCells / 7f).toInt() + if (totalCells % 7 != 0) 1 else 0

        repeat(weeks) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (i in 0..6) {
                    val cellIndex = it * 7 + i
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (cellIndex >= firstDayOfWeek && day <= daysInMonth) {
                            val thisDate = month.atDay(day)
                            DayCell(
                                day = day,
                                isToday = thisDate == today
                            )
                            day++
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun DayCell(day: Int, isToday: Boolean) {
    val bgColor = if (isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
    val textColor = if (isToday) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}