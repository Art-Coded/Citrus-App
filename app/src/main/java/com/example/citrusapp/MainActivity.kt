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
import androidx.compose.runtime.remember
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
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

//MAIN
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitrusAppTheme {
                val navController = rememberNavController()
                val startDestination = remember {
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user != null && user.isEmailVerified) {
                        "BottomNav"
                    } else {
                        "onboarding"
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
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
                            BottomNavScreen(rootNavController = navController)
                        }

                    }
                }
            }
        }
    }
}
