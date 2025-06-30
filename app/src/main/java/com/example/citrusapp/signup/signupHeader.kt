package com.example.citrusapp.signup

import SlideOne
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.citrusapp.ComponentsReusable.PagerIndicator
import com.example.citrusapp.R
import com.example.citrusapp.signup.slides.SlideThree
import com.example.citrusapp.signup.slides.SlideTwo
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(loginClick: () -> Unit, loginClick1: () -> Unit) {
    val pageCount = 3
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })
    val isDarkTheme = isSystemInDarkTheme()

    val coroutineScope = rememberCoroutineScope()
    fun navigateToSlide(index: Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }

    fun handleBackButton() {
        when (pagerState.currentPage) {
            0 -> loginClick()
            1 -> navigateToSlide(0)
            2 -> navigateToSlide(1)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { handleBackButton() },
                    modifier = Modifier
                        .height(46.dp)
                        .padding(start = 16.dp, top = 18.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.citruslogo),
                    contentDescription = "Citrus Logo",
                    modifier = Modifier
                        .height(44.dp)
                        .padding(top = 7.5.dp, end = 58.dp),
                    colorFilter = ColorFilter.tint(
                        if (isDarkTheme) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground
                    )
                )

                Spacer(modifier = Modifier.weight(1f))
            }


            PagerIndicator(
                pageCount = pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> SlideOne(
                        loginClick1 = { loginClick1() },
                        onNextClick = { navigateToSlide(1)}
                    )
                    1 -> SlideTwo(
                        onNextClick = { navigateToSlide(2) }
                    )
                    2 -> SlideThree(
                        loginClick1 = { loginClick1() },
                        isDarkTheme = isSystemInDarkTheme()
                    )

                }
            }
        }
    }
}
