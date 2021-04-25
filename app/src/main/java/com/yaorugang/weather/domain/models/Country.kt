package com.yaorugang.weather.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val id: String,
    val name: String
) : Parcelable