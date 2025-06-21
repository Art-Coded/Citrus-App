package com.example.citrusapp.Main.BottomNav

import BottomNavItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.citrusapp.Main.Account.AccountEdit.AccountEditScreen
import com.example.citrusapp.Main.Account.AccountScreen
import com.example.citrusapp.Main.Account.Contribute.ContributeScreen
import com.example.citrusapp.Main.Account.Logout.LogoutScreen
import com.example.citrusapp.Main.Account.Notification.NotificationScreen
import com.example.citrusapp.Main.Account.Privacy.PrivacyScreen
import com.example.citrusapp.Main.Account.Report.ReportScreen
import com.example.citrusapp.Main.Account.Support.SupportScreen
import com.example.citrusapp.Main.Account.SwitchAccount.SwitchAccountScreen
import com.example.citrusapp.Main.Account.Terms.TermsScreen
import com.example.citrusapp.Main.Home.AgriScreen
import com.example.citrusapp.Main.Home.CCISScreen
import com.example.citrusapp.Main.Home.CEAScreen
import com.example.citrusapp.Main.Home.CriminologyScreen
import com.example.citrusapp.Main.Home.EducScreen
import com.example.citrusapp.Main.Home.GradScreen
import com.example.citrusapp.Main.Home.HomeScreen
import com.example.citrusapp.Main.Home.ManagementScreen
import com.example.citrusapp.Main.Home.NursingScreen
import com.example.citrusapp.Main.Home.Shortcuts.GradesScreen
import com.example.citrusapp.Main.Home.Shortcuts.LibraryScreen
import com.example.citrusapp.Main.Home.Shortcuts.LostFoundScreen
import com.example.citrusapp.Main.Home.Shortcuts.PaymentsScreen
import com.example.citrusapp.Main.Home.Shortcuts.SchoolMapScreen
import com.example.citrusapp.Main.Inbox.InboxScreen
import com.example.citrusapp.Main.LMS.LMSScreen
import com.example.citrusapp.Main.LMS.MyCourses.AddCourseScreen
import com.example.citrusapp.Main.Network.FindWorks.CreateWorksScreen
import com.example.citrusapp.Main.Network.FindWorks.FindWorksScreen
import com.example.citrusapp.Main.Network.NetworkScreen
import com.example.citrusapp.Main.Network.Services.CreateServiceScreen
import com.example.citrusapp.Main.Network.Services.ServicesScreen

sealed class NavItem(val route: String, val label: String, val lottieIcon: String?) {
    object Home : NavItem("home", "Home", "home")
    object LMS : NavItem("lms", "LMS", "article_icon")
    object Network : NavItem("network", "Network", "newspaper")
    object Inbox : NavItem("inbox", "Inbox", "inbox")
    object Account : NavItem("account", "Account", null)
}

@Composable
fun BottomNavScreen() {
    val navController = rememberNavController()
    val isDarkTheme = isSystemInDarkTheme()

    val items = listOf(
        NavItem.Home,
        NavItem.LMS,
        NavItem.Network,
        NavItem.Inbox,
        NavItem.Account
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val bottomNavRoutes = items.map { it.route }
    val showBottomNav = currentRoute in bottomNavRoutes

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomNav,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut(),
                modifier = Modifier.height(60.dp)
            ) {
                BottomNavBar(navController = navController, items = items, isDarkTheme = isDarkTheme)
            }
        }
    ) { innerPadding ->
        // Animate the padding change
        val bottomPadding by animateDpAsState(
            targetValue = if (showBottomNav) 60.dp else 0.dp,
            animationSpec = tween(durationMillis = 100)
        )

        NavHost(
            navController = navController,
            startDestination = NavItem.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding),  // Use the animated padding here
            enterTransition = { fadeIn(animationSpec = tween(100)) },
            exitTransition = { fadeOut(animationSpec = tween(100)) },
        ) {
            composable("home") { backStackEntry ->
                HomeScreen(navController = navController)
            }
            composable("lms") {
                LMSScreen(navController = navController)
            }
            composable("network") {
                NetworkScreen(navController = navController)
            }
            composable("inbox") {
                InboxScreen()
            }
            composable("account") {
                AccountScreen(navController = navController)
            }

            // Home routes
            composable("library") {
                LibraryScreen(navController = navController)
            }
            composable("lostfound") {
                LostFoundScreen(navController = navController)
            }
            composable("grades") {
                GradesScreen()
            }
            composable("payments") {
                PaymentsScreen()
            }
            composable("schoolmap") {
                SchoolMapScreen()
            }

            //college routes
            composable("cea") {
                CEAScreen(navController = navController)
            }
            composable("education") {
                EducScreen(navController = navController)
            }
            composable("management") {
                ManagementScreen(navController = navController)
            }
            composable("ccis") {
                CCISScreen(navController = navController)
            }
            composable("criminology") {
                CriminologyScreen(navController = navController)
            }
            composable("agriculture") {
                AgriScreen(navController = navController)
            }
            composable("nursing") {
                NursingScreen(navController = navController)
            }
            composable("graduate") {
                GradScreen(navController = navController)
            }

            //Network routes
            composable("findworks") {
                FindWorksScreen(navController = navController)
            }
            composable("createworks") {
                CreateWorksScreen(navController = navController)
            }

            composable("services") {
                ServicesScreen(navController = navController)
            }
            composable("createservices") {
                CreateServiceScreen(navController = navController)
            }

            //LMS routes
            composable("addCourse") {
                AddCourseScreen(navController = navController)
            }

            //Account routes
            composable("accountEdit") {
                AccountEditScreen(navController = navController)
            }
            composable("privacy") {
                PrivacyScreen(navController = navController)
            }
            composable("notification") {
                NotificationScreen(navController = navController)
            }
            composable("report") {
                ReportScreen(navController = navController)
            }
            composable("support") {
                SupportScreen(navController = navController)
            }
            composable("terms") {
                TermsScreen(navController = navController)
            }
            composable("contribute") {
                ContributeScreen()
            }
            composable("switchacc") {
                SwitchAccountScreen()
            }
            composable("logout") {
                LogoutScreen()
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavHostController,
    items: List<NavItem>,
    isDarkTheme: Boolean
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    // List of bottom nav routes
    val bottomNavRoutes = items.map { it.route }

    // Only show if current route is a bottom nav route
    val showBottomNav = currentRoute in bottomNavRoutes

    AnimatedVisibility(
        visible = showBottomNav,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut(),
        modifier = Modifier.height(60.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp)
                        .alpha(0.6f)
                        .background(MaterialTheme.colorScheme.onSurface)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items.forEach { item ->
                        BottomNavItem(
                            label = item.label,
                            animationFile = item.lottieIcon,
                            isSelected = currentRoute == item.route,
                            isDarkTheme = isDarkTheme
                        ) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            }
        }
    }
}

