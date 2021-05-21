package com.rocksolidapps.movies.ui

sealed class DataState<out T> {
    object LoadingState : DataState<Nothing>()
    data class Data<out T>(val data: T) : DataState<T>()
    data class ErrorState(val resString: Int? = null, val errorMessage: String? = null) : DataState<Nothing>()

    fun getDataOrNull(): T? {
        return (this as? Data<T>)?.data
    }
}