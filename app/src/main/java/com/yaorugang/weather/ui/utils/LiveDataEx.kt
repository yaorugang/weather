package com.yaorugang.weather.ui

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

@MainThread
fun <A, B, C, R> liveDataChecker(
    firstSource: LiveData<A>,
    secondSource: LiveData<B>,
    thirdSource: LiveData<C>,
    zipFunc: (A?, B?, C?) -> R): LiveData<R> {

    val combinedLiveData = MediatorLiveData<R>()

    combinedLiveData.addSource(firstSource) {
        combinedLiveData.value = zipFunc(firstSource.value, secondSource.value, thirdSource.value)
    }
    combinedLiveData.addSource(secondSource) {
        combinedLiveData.value = zipFunc(firstSource.value, secondSource.value, thirdSource.value)
    }
    combinedLiveData.addSource(thirdSource) {
        combinedLiveData.value = zipFunc(firstSource.value, secondSource.value, thirdSource.value)
    }

    return combinedLiveData
}