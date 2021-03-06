package com.wolken.weatherForecast.features.main.weathermodels

import com.squareup.moshi.Json

data class Condition(

	@Json(name="code")
	val code: Int? = null,

	@Json(name="icon")
	val icon: String? = null,

	@Json(name="text")
	val text: String? = null
)