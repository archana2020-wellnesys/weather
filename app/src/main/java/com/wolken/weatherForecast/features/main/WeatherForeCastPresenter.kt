package com.wolken.weatherForecast.features.main

import android.annotation.SuppressLint
import com.wolken.weatherForecast.data.DataManager
import com.wolken.weatherForecast.features.base.BasePresenter
import com.wolken.weatherForecast.features.main.weathermodels.ResponseWeatherModels
import com.wolken.weatherForecast.injection.ConfigPersistent
import com.wolken.weatherForecast.util.NetworkUtil
import com.wolken.weatherForecast.util.rx.scheduler.SchedulerUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class WeatherForeCastPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<WeatherForecastMvpView>() {

    /*fun getPokemon(limit: Int) {
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain<List<String>>())
                .subscribe({ pokemons ->
                    mvpView?.apply {
                        showProgress(false)
                        showWeatherList(pokemons)
                    }
                }) { throwable ->
                    mvpView?.apply {
                        showProgress(false)
                        showError(throwable)
                    }
                }
    }*/

    @SuppressLint("CheckResult")
    fun getWeatherData(key: String?, q: String?, days: Int?) {
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.getTheWeatherData(key, q, days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weatheRes ->
                    
                    mvpView?.apply {
                        showProgress(false)
                        showWeatherList(listOf(weatheRes))

                    }


                }, { throwable ->

                    mvpView?.apply {

                        showProgress(false)
                        showError(throwable)
                    }
                })


    }
}