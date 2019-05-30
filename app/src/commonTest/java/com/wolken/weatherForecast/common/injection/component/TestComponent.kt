package com.wolken.weatherForecast.common.injection.component

import dagger.Component
import com.wolken.weatherForecast.common.injection.module.ApplicationTestModule
import com.wolken.weatherForecast.injection.component.AppComponent
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : AppComponent