package com.example.citrusapp.Main.Home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citrusapp.Main.Home.Shortcuts.Shortcuts
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import kotlinx.coroutines.launch
//MEOW
@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isDarkTheme = isSystemInDarkTheme()
    val scrollState = rememberScrollState()
    val isAtTop by remember { derivedStateOf { scrollState.value == 0 } }
    val coroutineScope = rememberCoroutineScope()

    //Calendar Redirection
    val scrollToCalendar: () -> Unit = {
        coroutineScope.launch {
            scrollState.animateScrollTo(2200)
        }
    }

    HomeDrawer(
        drawerState = drawerState
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
                            text = "Citrus NWSSU",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu Icon",
                            modifier = Modifier
                                .height(34.dp)
                                .clip(CircleShape)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                            colorFilter = ColorFilter.tint(
                                if (isDarkTheme) MaterialTheme.colorScheme.onBackground
                                else MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Featured Posts",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )

                        HorizontalDivider()
                    }

                    ViewPagerSection()

                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Spacer(modifier = Modifier.height(2.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(450.dp)
                                .shadow(8.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.surface)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Top
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Quick Actions",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )

                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "Info",
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .clickable
                                            {
                                                //TODO: INFO CLICK
                                            }
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                HorizontalDivider()

                                Spacer(modifier = Modifier.height(24.dp))

                                Shortcuts(navController = navController, scrollToCalendar = scrollToCalendar)
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "Calendar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )

                        HorizontalDivider()

                        Spacer(modifier = Modifier.height(16.dp))

                        Calendar()

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "Survey",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )

                        HorizontalDivider()

                        Spacer(modifier = Modifier.height(16.dp))

                        Survey()

                        Spacer(modifier = Modifier.height(32.dp))

                        Row {
                            Text(
                                text = "Courses",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                            )
                            Text(
                                text = "(Swipable)",
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(start = 6.dp, top = 2.dp)
                            )
                        }

                        HorizontalDivider()

                        Spacer(modifier = Modifier.height(16.dp))

                        SwipableCardSection(
                            onCEAClick = { navController.navigate("cea") },
                            onEducClick = { navController.navigate("education") },
                            onManagementClick = { navController.navigate("management") },
                            onCCISClick = { navController.navigate("ccis") },
                            onCriminologyClick = { navController.navigate("criminology") },
                            onAgriClick = { navController.navigate("agriculture") },
                            onNursingClick = { navController.navigate("nursing") },
                            onGradClick = { navController.navigate("graduate") }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(blue_green)
                        .clickable { /*TODO: FAB */ }
                ) {

                    Box(
                        modifier = Modifier
                            .widthIn(min = 0.dp)
                            .animateContentSize()
                    ) {
                        if (isAtTop) {
                            Text(
                                text = "Ask Citrus AI",
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                color = blue_green,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.schoollogo),
                            contentDescription = "Ask Citrus AI",
                            modifier = Modifier.size(40.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}