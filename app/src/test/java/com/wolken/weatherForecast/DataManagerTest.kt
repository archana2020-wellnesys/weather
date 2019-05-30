package com.wolken.weatherForecast

import com.nhaarman.mockito_kotlin.mock
import com.wolken.weatherForecast.common.TestDataFactory
import com.wolken.weatherForecast.data.DataManager
import com.wolken.weatherForecast.data.model.PokemonListResponse
import com.wolken.weatherForecast.data.remote.WeatherForecastServiceApi
import com.wolken.weatherForecast.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    @Rule @JvmField val overrideSchedulersRule = RxSchedulersOverrideRule()
    val namedResourceList = TestDataFactory.makeNamedResourceList(5)
    val pokemonListResponse = PokemonListResponse(namedResourceList)
    val name = "charmander"
    val pokemon = TestDataFactory.makePokemon(name)

    val mockPokemonApi: WeatherForecastServiceApi = mock {
        on { getPokemonList(anyInt()) } doReturn Single.just(pokemonListResponse)
        on { getPokemon(anyString()) } doReturn Single.just(pokemon)
    }

    private var dataManager = DataManager(mockPokemonApi)

    @Test
    fun getPokemonListCompletesAndEmitsPokemonList() {
        dataManager.getPokemonList(10)
                .test()
                .assertComplete()
                .assertValue(TestDataFactory.makePokemonNameList(namedResourceList))
    }

    @Test
    fun getPokemonCompletesAndEmitsPokemon() {
        dataManager.getPokemon(name)
                .test()
                .assertComplete()
                .assertValue(pokemon)
    }
}
