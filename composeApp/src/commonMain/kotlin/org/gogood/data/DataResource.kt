package org.gogood.data

sealed class DataResource<out R> {
    data object Loading : DataResource<Nothing>()
    data class Success<out T>(val data: T) : DataResource<T>()
    data class Error(val exception: Exception) : DataResource<Nothing>()
}