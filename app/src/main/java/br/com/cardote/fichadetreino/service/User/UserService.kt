package br.com.cardote.fichadetreino.service.User

import br.com.cardote.fichadetreino.models.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("app/user/{usuario}{senha}")
    fun login(@Query("usuario") username: String,
              @Query("senha") password: String) : Call<Boolean>

    @POST("app/user/")
    fun register(@Body user: User) : Call<User>
}