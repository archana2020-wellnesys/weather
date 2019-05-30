package com.wolken.weatherForecast.data.remote



import com.wolken.weatherForecast.features.main.weathermodels.ResponseWeatherModels
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherForecastServiceApi {


    @GET("v1/forecast.json")
    fun getWeathetDataForFourDays(@Query("key")key:String?,
                                  @Query("q")q:String?,@Query("days")days:Int?):Observable<ResponseWeatherModels>



}
