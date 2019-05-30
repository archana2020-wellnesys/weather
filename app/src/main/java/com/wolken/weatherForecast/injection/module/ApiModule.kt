package com.wolken.weatherForecast.injection.module

import dagger.Module
import dagger.Provides
import com.wolken.weatherForecast.data.remote.WeatherForecastServiceApi
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @Singleton
    internal fun providePokemonApi(retrofit: Retrofit): WeatherForecastServiceApi =
            retrofit.create(WeatherForecastServiceApi::class.java)
}