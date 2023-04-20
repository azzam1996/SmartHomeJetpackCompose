package com.azzam.smarthomejetpackcompose.presentation

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.compose.rememberNavController
import com.azzam.smarthomejetpackcompose.navigation.Navigation
import com.azzam.smarthomejetpackcompose.ui.theme.Eminence
import com.azzam.smarthomejetpackcompose.ui.theme.SmartHomeJetpackComposeTheme
import com.azzam.smarthomejetpackcompose.ui.theme.Valentino
import com.azzam.smarthomejetpackcompose.ui.theme.Valentino_1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setContent {
            SmartHomeJetpackComposeTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Valentino_1, Valentino, Eminence)
                            )
                        )
                ) {
                    Navigation(navHostController = rememberNavController())
                }

            }
        }
    }
}