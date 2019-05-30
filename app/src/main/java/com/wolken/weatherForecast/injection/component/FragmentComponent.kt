package com.wolken.weatherForecast.injection.component

import com.wolken.weatherForecast.injection.PerFragment
import com.wolken.weatherForecast.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent