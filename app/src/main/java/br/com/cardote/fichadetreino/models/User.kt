package br.com.cardote.fichadetreino.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (

    @SerializedName("usuario") var user: String? = null,
    @SerializedName("senha")var password: String? = null
) : Serializable