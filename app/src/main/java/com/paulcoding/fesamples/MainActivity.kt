package com.paulcoding.fesamples

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paulcoding.fesamples.core.ui.Routes
import com.paulcoding.fesamples.feature.home.navigation.homeScreen
import com.paulcoding.fesamples.feature.home.navigation.navigateToHome
import com.paulcoding.fesamples.feature.qroverlay.QROverlayActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                MainNavigation(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    val activity = LocalActivity.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.Main,
        enterTransition = {
            fadeIn(
                animationSpec = tween(220, easing = LinearEasing)
            ) + slideIntoContainer(
                animationSpec = tween(220, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(280, easing = LinearEasing)
            ) + slideOutOfContainer(
                animationSpec = tween(280, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = tween(220, easing = LinearEasing)
            )
        },
    ) {
        mainScreen(
            goHome = navController::navigateToHome,
            goQrOverlay = {
                activity?.startActivity(
                    Intent(activity, QROverlayActivity::class.java)
                )
            }
        )
        homeScreen()
    }
}

private fun NavGraphBuilder.mainScreen(
    goHome: () -> Unit,
    goQrOverlay: () -> Unit,
) {
    composable<Routes.Main>() {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)) {
            Button(onClick = goHome) {
                Text("Home")
            }
            Button(onClick = goQrOverlay) {
                Text("Qr Overlay")
            }
        }
    }
}