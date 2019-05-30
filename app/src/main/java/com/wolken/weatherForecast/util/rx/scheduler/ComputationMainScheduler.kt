package com.wolken.weatherForecast.util.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class ComputationMainScheduler<T> private constructor() : BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread())