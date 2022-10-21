package com.historyappart.romehistory.famouspeople


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FamousPersonListItem(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?
):Parcelable