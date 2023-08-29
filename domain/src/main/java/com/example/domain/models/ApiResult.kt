package com.example.domain.models

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}  // for your case might be simplify to use only sealed class

sealed class ApiResult<out T>(val status: ApiStatus, val data: T?, val message: String?) {

    data class Success<out R>(val _data: R?) : ApiResult<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: ErrorResponse) : ApiResult<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        message = exception.message
    )

    data class Loading(val isLoading: Boolean) : ApiResult<Nothing>(
        status = ApiStatus.LOADING,
        data = null,
        message = null
    )
}