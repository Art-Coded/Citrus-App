package com.example.citrusapp.onboardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.ComponentsReusable.PagerIndicator
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onLoginClick: () -> Unit, signupClick: () -> Unit) {
    val realPageCount = 3
    val isDarkTheme = isSystemInDarkTheme()

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { realPageCount }
    )

    val pages = listOf(
        Triple(R.drawable.screen1, "Welcome to Citrus", "Your all-in-one companion for Northwest Samar State University life! Whether you're looking for the latest campus news and events, need to track your class schedule and more, Citrus has got you covered!"),
        Triple(R.drawable.screen2, "Stay Informed", "Manage classes, track events, get real-time updates, and stay organized with to-do lists and custom deadlines."),
        Triple(R.drawable.screen3, "Connect, Grow, and Find Opportunities", "Networking is key to success! Citrus helps you connect with peers, alumni, and potential mentors through our Find Works feature and more!")
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.citruslogo),
                contentDescription = "Citrus Logo",
                modifier = Modifier
                    .padding(top = 8.dp, start = 4.dp)
                    .height(36.dp),
                colorFilter = ColorFilter.tint(
                    if (isDarkTheme) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground
                )
            )

            Spacer(modifier = Modifier.height(12.dp))
            PagerIndicator(
                pageCount = realPageCount,
                currentPage = pagerState.currentPage
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            BlobBackground(
                pagerState = pagerState,
                realPageCount = realPageCount,
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { clip = false }
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                val (imageRes, title, description) = pages[index]
                val blobRes = when (index) {
                    0 -> R.drawable.blob1
                    1 -> R.drawable.blob2
                    else -> R.drawable.blob
                }
                OnboardingPage(imageRes = imageRes, title = title, description = description, blobRes = blobRes)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    onLoginClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Text(text = "Login")
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
                    .padding(top = 4.dp, bottom = 40.dp)
                    .clickable(
                        onClick = {
                            signupClick()
                        },
                        role = Role.Button
                    )
                    .indication(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    )
            )
        }
    }
}

@Composable
fun OnboardingPage(
    imageRes: Int,
    title: String,
    description: String,
    blobRes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { clip = false },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = blobRes),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(blue_green)
            )

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .padding(start = 32.dp, end = 32.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp,
                lineHeight = 16.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun BlobBackground(
    pagerState: PagerState,
    realPageCount: Int,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val containerWidth = maxWidth
        val totalWidth = containerWidth * (realPageCount - 1)

        val currentPage = pagerState.currentPage
        val pageOffset = pagerState.currentPageOffsetFraction
        val scrollPosition = (currentPage + pageOffset) * containerWidth.value

        val centerPageOffset = containerWidth.value

        Image(
            painter = painterResource(id = R.drawable.blob),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(blue_green),
            modifier = Modifier
                .size(450.dp)
                .fillMaxHeight()
                .width(totalWidth + containerWidth)
                .graphicsLayer {
                    translationX = -scrollPosition + centerPageOffset
                }
                .align(Alignment.Center)
                .alpha(0.3f)
        )
    }
}