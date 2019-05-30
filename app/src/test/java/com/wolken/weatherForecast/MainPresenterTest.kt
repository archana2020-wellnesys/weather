package com.wolken.weatherForecast

import com.nhaarman.mockito_kotlin.*
import com.wolken.weatherForecast.common.TestDataFactory
import com.wolken.weatherForecast.features.main.WeatherForecastMvpView
import com.wolken.weatherForecast.features.main.WeatherForeCastPresenter
import com.wolken.weatherForecast.util.RxSchedulersOverrideRule
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    val pokemonList = TestDataFactory.makePokemonNamesList(10)

    val mockWeatherForecastMvpView: WeatherForecastMvpView = mock()
   /* val mockDataManager: DataManager = mock {
        on { getPokemonList(10) } doReturn Single.just(pokemonList)
        on { getPokemonList(5) } doReturn Single.error<List<String>>(RuntimeException())
    }*/
    private var weatherForeCastPresenter: WeatherForeCastPresenter? = null

    @JvmField
    @Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

   /* @Before
    fun setUp() {
        weatherForeCastPresenter = WeatherForeCastPresenter(mockDataManager)
        weatherForeCastPresenter?.attachView(mockWeatherForecastMvpView)
    }*/

    @After
    fun tearDown() {
        weatherForeCastPresenter?.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonReturnsPokemonNames() {

        weatherForeCastPresenter?.getPokemon(10)

        verify(mockWeatherForecastMvpView, times(2)).showProgress(anyBoolean())
        verify(mockWeatherForecastMvpView).showWeatherList(pokemonList)
        verify(mockWeatherForecastMvpView, never()).showError(RuntimeException())

    }

    @Test
    @Throws(Exception::class)
    fun getPokemonReturnsError() {

        weatherForeCastPresenter?.getPokemon(5)

        verify(mockWeatherForecastMvpView, times(2)).showProgress(anyBoolean())
        verify(mockWeatherForecastMvpView).showError(any())
        verify(mockWeatherForecastMvpView, never()).showWeatherList(any())
    }
}