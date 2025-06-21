package com.example.citrusapp.Main.LMS

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.citrusapp.Main.LMS.Dashboard.DashboardTab
import com.example.citrusapp.Main.LMS.MyCourses.MyCoursesTab
import com.example.citrusapp.R

@Composable
fun LMSScreen(navController: NavHostController) {
    val tabTitles = listOf("Dashboard", "My Courses")
    val pagerState = rememberPagerState { tabTitles.size }
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Track whether we're showing the header (Dashboard tab) - fixed with remember
    val showHeader by remember {
        derivedStateOf { pagerState.currentPage == 0 }
    }

    // Sync the pager state with the tab state
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    LaunchedEffect(selectedTabIndex) {
        if (selectedTabIndex != pagerState.currentPage) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
    }

    // Define static target heights
    val dashboardAppBarHeight = 170.dp
    val coursesAppBarHeight = 68.dp

    // Animate height based on selected tab
    val targetHeight by animateDpAsState(
        targetValue = if (showHeader) dashboardAppBarHeight else coursesAppBarHeight,
        animationSpec = tween(durationMillis = 300),
        label = "AppBarHeightAnimation"
    )

    // Animation for header content
    val headerAlpha by animateFloatAsState(
        targetValue = if (showHeader) 1f else 0f,
        animationSpec = tween(durationMillis = 200)
    )
    val headerHeight by animateDpAsState(
        targetValue = if (showHeader) dashboardAppBarHeight - coursesAppBarHeight else 0.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Animated App Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(targetHeight)
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Animated Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
                        .alpha(headerAlpha),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Art Lyndone Acuesta Hemplo",
                                style = MaterialTheme.typography.headlineMedium,
                                fontSize = 26.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(onClick = { /* TODO: Handle dropdown click */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_more),
                                    contentDescription = "Dropdown menu"
                                )
                            }
                        }

                        Row{
                            Text(
                                text = "22-00489",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(text = title) }
                        )
                    }
                }
            }
        }

        // Swipeable content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> DashboardTab(rememberLazyListState())
                1 -> MyCoursesTab(rememberLazyListState(), navController)
            }
        }
    }
}