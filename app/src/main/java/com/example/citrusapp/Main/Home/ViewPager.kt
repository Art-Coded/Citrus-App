package com.example.citrusapp.Main.Home

import AutoScrollPager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.citrusapp.ComponentsReusable.PagerIndicator
import com.example.citrusapp.R

@Composable
fun ViewPagerSection() {
    val pagerItems = listOf(
        R.drawable.viewpager1,
        R.drawable.viewpager2,
        R.drawable.viewpager3
    )

    val virtualPageCount = Int.MAX_VALUE
    val startPage = virtualPageCount / 2 - (virtualPageCount / 2) % pagerItems.size

    val pagerState = rememberPagerState(
        initialPage = startPage,
        pageCount = { virtualPageCount }
    )

    AutoScrollPager(pagerState = pagerState, delayMillis = 6000L)

    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val aspectRatio = 1243f / 721f
    val imageHeightDp = screenWidthDp / aspectRatio

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeightDp)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val actualPage = page % pagerItems.size
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Padding around the card
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(16.dp) // Rounded corners
                ) {
                    Image(
                        painter = painterResource(id = pagerItems[actualPage]),
                        contentDescription = "Slide ${actualPage + 1}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        // ✅ Reserve indicator space even if you later hide the indicator conditionally
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp), // Reserve enough vertical space for the indicator
            contentAlignment = Alignment.Center
        ) {
            PagerIndicator(
                pageCount = pagerItems.size,
                currentPage = pagerState.currentPage % pagerItems.size,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

