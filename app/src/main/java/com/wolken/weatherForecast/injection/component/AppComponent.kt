package com.wolken.weatherForecast.injection.component

import android.app.Application
import android.content.Context
import dagger.Component
import com.wolken.weatherForecast.data.DataManager
import com.wolken.weatherForecast.data.remote.WeatherForecastServiceApi
import com.wolken.weatherForecast.injection.ApplicationContext
import com.wolken.weatherForecast.injection.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun pokemonApi(): WeatherForecastServiceApi
}
