package com.wolken.weatherForecast.runner

import com.wolken.weatherForecast.util.RxIdlingExecutionHook
import com.wolken.weatherForecast.util.RxIdlingResource
import android.os.Bundle
import androidx.test.espresso.Espresso
import rx.plugins.RxJavaPlugins

/**
 * Runner that registers a Espresso Indling resource that handles waiting for
 * RxJava Observables to finish.
 * WARNING - Using this runner will block the tests if the application uses long-lived hot
 * Observables such us event buses, etc.
 */
class RxAndroidJUnitRunner : UnlockDeviceAndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)
        val rxIdlingResource = RxIdlingResource()
        RxJavaPlugins.getInstance()
                .registerObservableExecutionHook(RxIdlingExecutionHook(rxIdlingResource))
        Espresso.registerIdlingResources(rxIdlingResource)
    }
}