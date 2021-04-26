package com.yaorugang.weather.ui.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

@MainThread
fun <A, R> liveDataChecker(
    firstSource: LiveData<A>,
    zipFunc: (A?) -> R): LiveData<R> {

    val combinedLiveData = MediatorLiveData<R>()

    combinedLiveData.addSource(firstSource) {
        combinedLiveData.value = zipFunc(firstSource.value)
    }

    return combinedLiveData
}

@MainThread
fun <A, B, R> liveDataChecker(
    firstSource: LiveData<A>,
    secondSource: LiveData<B>,
    zipFunc: (A?, B?) -> R): LiveData<R> {

    val combinedLiveData = MediatorLiveData<R>()

    combinedLiveData.addSource(firstSource) {
        combinedLiveData.value = zipFunc(firstSource.value, secondSource.value)
    }
    combinedLiveData.addSource(secondSource) {
        combinedLiveData.value = zipFunc(firstSource.value, secondSource.value)
    }

    return combinedLiveData
}