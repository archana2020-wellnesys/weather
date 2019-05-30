package com.wolken.weatherForecast.util.rx.scheduler



object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
