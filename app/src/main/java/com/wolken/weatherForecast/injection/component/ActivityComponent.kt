package com.wolken.weatherForecast.injection.component

import com.wolken.weatherForecast.injection.PerActivity
import com.wolken.weatherForecast.injection.module.ActivityModule
import com.wolken.weatherForecast.features.base.BaseActivity
import com.wolken.weatherForecast.features.main.WeatherForeCastActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(weatherForeCastActivity: WeatherForeCastActivity)


}
