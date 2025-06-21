package com.example.citrusapp.Main.LMS.MyCourses

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import kotlin.random.Random

@Composable
fun MyCoursesTab(listState: LazyListState, navController: NavHostController) {
    val courses = listOf(
        Course("Mobile App Development", "BSCS 3A", "Mr. Pael"),
        Course("Web Development", "BSCS 3B", "Mr. Bacamante"),
        Course("Data Structures", "BSCS 2A", "Dr. DickFace"),
        Course("Machine Learning", "BSCS 4A", "Prof. CHATGPT"),
        Course("Software Engineering", "BSCS 3A", "Mr. Mark Give me the ZUCC")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(courses.size) { index ->
                val course = courses[index]
                val baseColor = generateRandomColor(index)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(126.dp)
                        .clickable {
                            //TODO: COURSE CLICK
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        baseColor.darken(0.4f),
                                        baseColor.darken(0.2f),
                                        baseColor.darken(0.3f)
                                    )
                                )
                            )
                    ) {
                        // Title Row with Icon
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = course.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontSize = 20.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    // TODO: Add action here
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_more),
                                    contentDescription = "More Options",
                                    tint = Color.White
                                )
                            }
                        }

                        // Section and Instructor Info
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = course.section,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            Text(
                                text = "Instructor: ${course.instructor}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 12.sp
                            )
                        }

                        // Watermark School Logo
                        Image(
                            painter = painterResource(id = R.drawable.schoollogo),
                            contentDescription = "School Logo",
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = 85.dp, y = 40.dp)
                                .graphicsLayer {
                                    alpha = 0.5f
                                }
                        )
                    }
                }
            }
        }

        // FAB
        FloatingActionButton(
            onClick = {
                navController.navigate("addCourse") {
                    launchSingleTop = true
                }
            },
            containerColor = blue_green,
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(56.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}

// Data class and helpers

data class Course(
    val title: String,
    val section: String,
    val instructor: String
)

fun generateRandomColor(index: Int): Color {
    val random = Random(index)
    return Color(
        red = random.nextInt(100, 150),
        green = random.nextInt(100, 256),
        blue = random.nextInt(100, 256)
    )
}

fun Color.darken(factor: Float): Color {
    return Color(
        red = (red * (1f - factor)).coerceIn(0f, 1f),
        green = (green * (1f - factor)).coerceIn(0f, 1f),
        blue = (blue * (1f - factor)).coerceIn(0f, 1f),
        alpha = alpha
    )
}
