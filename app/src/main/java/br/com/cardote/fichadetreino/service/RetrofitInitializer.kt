package br.com.cardote.fichadetreino.service

import br.com.cardote.fichadetreino.service.Training.TrainingService
import br.com.cardote.fichadetreino.service.User.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.google.gson.Gson



class RetrofitInitializer {

    val okHttp = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .build()

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://edster.com.br/service/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp)
            .build()


    fun trainingService() = retrofit.create(TrainingService::class.java)

    fun userService() = retrofit.create(UserService::class.java)
}