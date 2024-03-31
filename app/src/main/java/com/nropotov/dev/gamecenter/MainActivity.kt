package com.nropotov.dev.gamecenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nropotov.dev.gamecenter.ui.theme.GameCenterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameCenterTheme {
            }
        }
    }
}
