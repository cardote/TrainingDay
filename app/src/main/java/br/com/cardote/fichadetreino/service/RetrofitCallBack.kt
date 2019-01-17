package br.com.cardote.fichadetreino.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> callback(success: (response: Response<T>?) -> Unit,
                 failure: (throwable: Throwable?) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            success(response)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            failure(t)
        }
    }
}