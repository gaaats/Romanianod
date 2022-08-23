package com.passmathart.passgeneratorbatman


import com.google.gson.annotations.SerializedName

data class ResponseKeyvords(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("keywords")
    val keywords: List<Keyword?>?
)