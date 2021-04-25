package com.yaorugang.weather.data.network.mappers

import com.yaorugang.weather.domain.exceptions.*
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExceptionMapper @Inject constructor() {
    operator fun invoke(exception: Exception): DomainException {
        return when (exception) {
            is UnknownHostException -> NoNetworkException()
            is ConnectException -> NoNetworkException()
            is SocketTimeoutException -> TimeoutException()
            is CancellationException -> JobCancelledException()
            is HttpException -> mappingTo(exception.code(), exception.response()?.body().toString())
            else -> GenericException()
        }
    }

    private fun mappingTo(responseCode: Int, body: String?): DomainException =
        when (responseCode) {
            200 -> if (body == null) EmptyDataException() else GenericException()
            else -> GenericException()
        }
}
