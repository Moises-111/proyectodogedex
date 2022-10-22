package com.example.dogedex.api

import okhttp3.Interceptor
import okhttp3.Response
import java.lang.RuntimeException


object ApiServiceInterceptor:Interceptor {

    private var sessionToken:String?= null

    fun setSessionToken(sessionToken: String){
        this.sessionToken =  sessionToken
    }

    const val   NEEDS_AUTH_HEADER_KEY = "needs_authentication"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        //si el request necesita autenticacion
        if(request.header(NEEDS_AUTH_HEADER_KEY) != null) {
            if (sessionToken == null) {
                throw RuntimeException("Need to be authenticated to perform")
            } else {
                requestBuilder.addHeader("AUTH-TOKEN", sessionToken!!)
            }
        }
        return chain.proceed(requestBuilder.build())
    }

}