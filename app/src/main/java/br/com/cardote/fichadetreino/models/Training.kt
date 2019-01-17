package br.com.cardote.fichadetreino.models

import com.google.gson.annotations.SerializedName

data class Training(
    @SerializedName("id") var id: String? = null,
    @SerializedName("user") var user: String? = null,
    @SerializedName("muscle_group")var muscleGroup: String? = null,
    @SerializedName("day")var day: String? = null

)