package com.wolken.weatherForecast.features.main.weathermodels

import com.squareup.moshi.Json

data class Astro(

	@Json(name="moonset")
	val moonset: String? = null,

	@Json(name="sunrise")
	val sunrise: String? = null,

	@Json(name="sunset")
	val sunset: String? = null,

	@Json(name="moonrise")
	val moonrise: String? = null
)