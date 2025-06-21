package com.example.citrusapp.Main.Network

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.launch

@Composable
fun NetworkScreen(navController: NavController? = null) {
    val worksScrollState = rememberScrollState()
    val servicesScrollState = rememberScrollState()


    val selectedInterests = remember { mutableStateSetOf<String>() }

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val interests = listOf(
        "Technology", "Art", "Science", "Sports",
        "Music", "Management", "Education", "Engineering",
        "Cooking", "Photography", "Architecture", "Nursing",
        "Programming", "Designing", "Business", "Community Service",
        "Agriculture", "OJT/Internship"
    )

    val featuredPosts = listOf(
        "Example 1",
        "Example 2",
        "Example 3",
        "Example 4",
        "Example 5",
        "Example 6"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.schoollogo),
                    contentDescription = "School Logo",
                    modifier = Modifier
                        .height(34.dp)
                        .clip(CircleShape)
                        .clickable {
                            scope.launch {
                                scrollState.animateScrollTo(0)
                            }
                        }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Network",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "What are you interested in?",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 12.dp, start = 14.dp, end = 14.dp)
            )

            Text(
                text = "Select an item to improve your recommendations across Citrus Network",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, start = 14.dp, end = 14.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(start = 14.dp, end = 14.dp),
                mainAxisAlignment = MainAxisAlignment.Center,
                mainAxisSpacing = 8.dp,
            ) {
                interests.forEach { interest ->
                    InterestChip(
                        interest = interest,
                        isSelected = selectedInterests.contains(interest),
                        onSelectionChanged = { selected ->
                            if (selected) {
                                selectedInterests.add(interest)
                            } else {
                                selectedInterests.remove(interest)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // Handle button click with selected interests
                },
                enabled = selectedInterests.size >= 1,
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White,
                    disabledContainerColor = blue_green.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 50.dp)
            ) {
                Text(text = "Submit")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Available Jobs",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 14.dp, end = 14.dp)
                )

                val pressed = remember { mutableStateOf(false) }

                Text(
                    text = "View more",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 14.dp, end = 18.dp)
                        .graphicsLayer {
                            alpha = if (pressed.value) 0.5f else 1f
                        }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    pressed.value = true
                                    try {
                                        awaitRelease()
                                    } finally {
                                        pressed.value = false
                                    }
                                },
                                onTap = {
                                    navController?.navigate("findworks")
                                }
                            )
                        }

                )
            }


            HorizontalDivider(modifier = Modifier.padding(horizontal = 14.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(worksScrollState)
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                featuredPosts.forEach { post ->
                    FeaturedPostCard(title = post)
                }
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // This will space the items equally
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Available Services",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 14.dp, end = 14.dp)
                )

                val pressed = remember { mutableStateOf(false) }

                Text(
                    text = "View more",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 14.dp, end = 18.dp)
                        .graphicsLayer {
                            alpha = if (pressed.value) 0.5f else 1f
                        }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    pressed.value = true
                                    try {
                                        awaitRelease()
                                    } finally {
                                        pressed.value = false
                                    }
                                },
                                onTap = {
                                    navController?.navigate("services")
                                }
                            )
                        }

                )
            }

            HorizontalDivider(modifier = Modifier.padding(horizontal = 14.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(servicesScrollState)
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                featuredPosts.forEach { post ->
                    FeaturedPostCard(title = post)
                }
            }
        }
    }
}

@Composable
fun InterestChip(
    interest: String,
    isSelected: Boolean,
    onSelectionChanged: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .heightIn(min = 32.dp),
        shape = MaterialTheme.shapes.small,
        color = if (isSelected) MaterialTheme.colorScheme.onSurface
        else MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        onClick = { onSelectionChanged(!isSelected) }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = interest,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSelected) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(end = 4.dp)
            )
            Icon(
                painter = painterResource(
                    id = if (isSelected) R.drawable.ic_check
                    else R.drawable.ic_add
                ),
                contentDescription = if (isSelected) "Selected" else "Add interest",
                tint = if (isSelected) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun FeaturedPostCard(title: String) {
    Surface(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .padding(start = 6.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}