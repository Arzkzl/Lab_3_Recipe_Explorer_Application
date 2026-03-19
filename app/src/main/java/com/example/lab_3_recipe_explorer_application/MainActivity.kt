package com.example.lab_3_recipe_explorer_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

class MainActivity : ComponentActivity() {
    //this API is experimental in Material3, so OptIn is required to use WindowSizeClass
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //calculates the screen size of the device
            val windowSize= calculateWindowSizeClass(this)

            //calls the main composable of the app.
            RecipeExplorerApp(
                //passes the screen width size class(compact,medium,expanded)
                windowSize = windowSize.widthSizeClass
            )
        }
    }
}