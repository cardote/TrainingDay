package br.com.cardote.fichadetreino.service.Training

import br.com.cardote.fichadetreino.models.Training
import retrofit2.Call
import retrofit2.http.*

interface TrainingService {
    @GET("app/training/{usuario}")
    fun list(@Query("usuario") username: String): Call<List<Training>>

    @POST("app/training")
    fun insert(@Body training: Training) : Call<Training>

    @PUT("app/training")
    fun update(@Body training: Training) : Call<List<Training>>

    @DELETE("app/training/{id}")
    fun delete(@Query("id") id: String) : Call<Training>
}