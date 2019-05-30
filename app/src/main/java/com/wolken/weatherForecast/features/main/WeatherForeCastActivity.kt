package com.wolken.weatherForecast.features.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.wolken.weatherForecast.R
import com.wolken.weatherForecast.features.base.BaseActivity
import com.wolken.weatherForecast.features.common.ErrorView
import com.wolken.weatherForecast.features.main.weathermodels.ResponseWeatherModels
import com.wolken.weatherForecast.util.NetworkUtil
import com.wolken.weatherForecast.util.gone
import com.wolken.weatherForecast.util.visible
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject


class WeatherForeCastActivity : BaseActivity(),
        WeatherForecastMvpView, WeatherForeCastAdapter.ClickListener, ErrorView.ErrorListener {

    @Inject
    lateinit var weatherForeCastAdapter: WeatherForeCastAdapter
    @Inject
    lateinit var weatherForeCastPresenter: WeatherForeCastPresenter


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent().inject(this)
        weatherForeCastPresenter.attachView(this)



        try {

            if (NetworkUtil.isNetworkConnected(this@WeatherForeCastActivity)) {

                Handler().postDelayed({
                    weatherForeCastPresenter.getWeatherData(getString(R.string.wsd_rest_service_api_key), getString(R.string.location), 6)
                },1000)

            } else {
                Toast.makeText(this@WeatherForeCastActivity, "No Internet Found", Toast.LENGTH_LONG).show()
            }


        } catch (e: Exception) {
            Toast.makeText(this@WeatherForeCastActivity, e.toString(), Toast.LENGTH_LONG).show()
        }
        weatherForeCastAdapter.setClickListener(this)
        recyclerViewWeather?.apply {
            val resId = R.anim.layout_animation_from_bottom
            val animation = AnimationUtils.loadLayoutAnimation(this@WeatherForeCastActivity, resId)
            layoutAnimation = animation
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)

            adapter = weatherForeCastAdapter


        }

        viewError?.setErrorListener(this)


    }

    override fun layoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        weatherForeCastPresenter.detachView()
    }

    override fun showWeatherList(weatherList: List<ResponseWeatherModels>) {


        for (list in 0 until weatherList.size) {


            if (weatherList[list].current?.tempC.toString().isNotEmpty()) {
                tv_location_temp.text = weatherList[list].current?.tempC.toString() + " " + "C"
            }
            if (weatherList[list].location?.name.toString().isNotEmpty()) {
                tv_location_name.text = weatherList[list].location?.name.toString()
            }


        }

        weatherForeCastAdapter.apply {
            setWeatherList(weatherList)
            notifyDataSetChanged()
        }

        recyclerViewWeather?.visible()
        //swipeToRefresh?.visible()
    }

    override fun showProgress(show: Boolean) {

        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

    }

    override fun showError(error: Throwable) {
        frame_layout_main?.visibility = View.GONE
        recyclerViewWeather?.gone()
        progressBar.visibility = View.GONE
        viewError?.visible()
        Timber.e(error, "There was an error retrieving the weather list")
    }

    override fun onWeatherItem(pokemon: String) {
        //startActivity(DetailActivity.getStartIntent(this, pokemon))
    }

    override fun onReloadData() {
        // weatherForeCastPresenter.getPokemon(POKEMON_COUNT)
        try {

            if (NetworkUtil.isNetworkConnected(this@WeatherForeCastActivity)) {
                weatherForeCastPresenter.getWeatherData(getString(R.string.wsd_rest_service_api_key), getString(R.string.location), 6)
            } else {
                Toast.makeText(this@WeatherForeCastActivity, "No Internet Found", Toast.LENGTH_LONG).show()
            }


        } catch (e: Exception) {
            Toast.makeText(this@WeatherForeCastActivity, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

}