package com.nropotov.dev.gamecenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.nropotov.dev.gamecenter.ui.theme.GameCenterTheme
import com.nropotov.dev.games.presentation.GamesListScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as GamesApp).component.inject(this)

        setContent {
            GameCenterTheme {
                GamesListScreen(viewmodelFactory)
            }
        }
    }
}
