package com.nropotov.dev.gamecenter.di

import com.nropotov.dev.gamecenter.GamesApp
import com.nropotov.dev.gamecenter.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProvideGamesApiModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(application: GamesApp)

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}
