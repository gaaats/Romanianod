package com.excercisegenpackone.excercisegentriceps.recycler


import com.google.gson.annotations.SerializedName

data class ExercicesListrRsponseItem(
    @SerializedName("difficulty")
    val difficulty: String?,
    @SerializedName("equipment")
    val equipment: String?,
    @SerializedName("instructions")
    val instructions: String?,
    @SerializedName("muscle")
    val muscle: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?

) {
    fun mapToUiModel() = ExerciseItem(
        name = name ?: "default exercise",
        equipment = equipment ?: "no equipment"
    )
}