package com.wolken.weatherForecast.common

import com.wolken.weatherForecast.WeatherForecastStarterApplication
import com.wolken.weatherForecast.common.injection.component.DaggerTestComponent
import com.wolken.weatherForecast.common.injection.component.TestComponent
import com.wolken.weatherForecast.common.injection.module.ApplicationTestModule
import com.wolken.weatherForecast.data.DataManager
import android.content.Context
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
class TestComponentRule(val context: Context) : TestRule {

    val testComponent: TestComponent

    init {
        val application = WeatherForecastStarterApplication.get(context)
        testComponent = DaggerTestComponent.builder()
                .applicationTestModule(ApplicationTestModule(application))
                .build()
    }

    val mockDataManager: DataManager
        get() = testComponent.dataManager()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                val application = WeatherForecastStarterApplication.get(context)
                application.component = testComponent
                base.evaluate()
            }
        }
    }
}