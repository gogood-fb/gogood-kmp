package org.gogood.data.authentication

interface Authenticator {
    fun getAuthToken(): String?
}