package com.wolken.weatherForecast.features.main

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.wolken.weatherForecast.R
import com.wolken.weatherForecast.features.main.weathermodels.ResponseWeatherModels
import javax.inject.Inject



class WeatherForeCastAdapter @Inject
constructor() : RecyclerView.Adapter<WeatherForeCastAdapter.ItemViewHolder>() {

    private var weatherForeCastList: List<ResponseWeatherModels>
    private var clickListener: ClickListener? = null

    private var lastPosition = -1

    init {
        weatherForeCastList = ArrayList<ResponseWeatherModels>() as List<ResponseWeatherModels>
    }

    fun setWeatherList(weatherList: List<ResponseWeatherModels>) {
        weatherForeCastList = weatherList
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.adapter_weather_forecast, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list = weatherForeCastList[position]
        holder.bind(list)


    }

    override fun getItemCount(): Int {
        return weatherForeCastList.size
    }

    interface ClickListener {
        fun onWeatherItem(pokemon: String)
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        @BindView(R.id.tv_weather_day_tuesday)
        @JvmField
        var tv_weather_day_tuesday: TextView? = null

        @BindView(R.id.tv_weather_day_wednesday)
        @JvmField
        var tv_weather_day_wednesday: TextView? = null

        @BindView(R.id.tv_weather_day_thursday)
        @JvmField
        var tv_weather_day_thursday: TextView? = null


        @BindView(R.id.tv_weather_day_friday)
        @JvmField
        var tv_weather_day_friday: TextView? = null

        @BindView(R.id.tv_weather_temp_tuesday)
        @JvmField
        var tv_weather_temp_tuesday: TextView? = null

        @BindView(R.id.tv_weather_temp_wednesday)
        @JvmField
        var tv_weather_temp_wednesday: TextView? = null

        @BindView(R.id.tv_weather_temp_thursday)
        @JvmField
        var tv_weather_temp_thursday: TextView? = null

        @BindView(R.id.tv_weather_temp_friday)
        @JvmField
        var tv_weather_temp_friday: TextView? = null


        init {
            ButterKnife.bind(this, itemView)

        }

        fun bind(resWeatherModels: ResponseWeatherModels) {

            Log.d("resWeatherModels", "" + resWeatherModels)

            for (bindlist in 0 until resWeatherModels.forecast?.forecastday?.size!!) {

                //BIND TUESDAY DATA
                if (resWeatherModels.forecast.forecastday[bindlist]?.date.equals("2019-06-04")) {

                    tv_weather_day_tuesday?.text = "Tuesday"

                    val tueAvgTemp = resWeatherModels.forecast.forecastday[bindlist]?.day?.avgtempC
                    tv_weather_temp_tuesday?.text = tueAvgTemp.toString() + "C"
                }
                //WEDNESDAY DATA
                if (resWeatherModels.forecast.forecastday[bindlist]?.date.equals("2019-06-05")) {

                    tv_weather_day_wednesday?.text = "Wednesday"

                    val wedAvgTemp = resWeatherModels.forecast.forecastday[bindlist]?.day?.avgtempC
                    tv_weather_temp_wednesday?.text = wedAvgTemp.toString() + "C"
                }
                //THURSDAY DATA
                if (resWeatherModels.forecast.forecastday[bindlist]?.date.equals("2019-06-06")) {

                    tv_weather_day_thursday?.text = "Thursday"

                    val thursAvgTemp = resWeatherModels.forecast.forecastday[bindlist]?.day?.avgtempC
                    tv_weather_temp_thursday?.text = thursAvgTemp.toString() + "C"
                }
                //FRIDAY DATA
                if (resWeatherModels.forecast.forecastday[bindlist]?.date.equals("2019-06-07")) {

                    tv_weather_day_friday?.text = "Friday"

                    val friAvgTemp = resWeatherModels.forecast.forecastday[bindlist]?.day?.avgtempC
                    tv_weather_temp_friday?.text = friAvgTemp.toString() + "C"
                }

            }


        }
    }

}