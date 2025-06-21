package com.example.citrusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.citrusapp.Main.BottomNav.BottomNavScreen
import com.example.citrusapp.login.LoginScreen
import com.example.citrusapp.onboardingScreen.OnboardingScreen
import com.example.citrusapp.signup.SignupScreen
import com.example.citrusapp.ui.theme.CitrusAppTheme
//MAIN
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitrusAppTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "onboarding",
                        modifier = Modifier.padding(innerPadding),
                        enterTransition = { fadeIn(animationSpec = tween(200)) },
                        exitTransition = { fadeOut(animationSpec = tween(200)) },
                        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(200)) },
                        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(200)) }
                    ) {
                        composable("onboarding") {
                            OnboardingScreen(
                                onLoginClick = { navController.navigate("login") {popUpTo("onboarding") {inclusive = true}} },
                                signupClick = { navController.navigate("signup") {popUpTo("onboarding") {inclusive = true}} }

                            )
                        }
                        composable("login") {
                            LoginScreen(
                                onBoardingClick = { navController.navigate("onboarding") {popUpTo("login") {inclusive = true}} },
                                signupClick = { navController.navigate("signup") {popUpTo("login") {inclusive = true}} },
                                homeClick = { navController.navigate("BottomNav") {popUpTo("login") {inclusive = true}} }
                            )
                        }
                        composable("signup") {
                            SignupScreen(
                                loginClick = { navController.navigate("login") {popUpTo("signup") {inclusive = true}} },
                                loginClick1 = { navController.navigate("login") {popUpTo("signup") {inclusive = true}} }
                            )
                        }
                        composable("BottomNav") {
                            BottomNavScreen()
                        }

                    }
                }
            }
        }
    }
}
