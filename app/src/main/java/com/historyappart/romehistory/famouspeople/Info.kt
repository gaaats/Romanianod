package com.historyappart.romehistory.famouspeople


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    @SerializedName("awards")
    val awards: String?,
    @SerializedName("born")
    val born: String?,
    @SerializedName("cause_of_death")
    val causeOfDeath: String?,
    @SerializedName("children")
    val children: List<String?>?,
    @SerializedName("conflicts")
    val conflicts: List<String?>?,
    @SerializedName("died")
    val died: String?,
    @SerializedName("notable_work")
    val notableWork: List<String?>?,
    @SerializedName("occupation")
    val occupation: List<String?>?,
    @SerializedName("office")
    val office: List<String?>?,
    @SerializedName("parents")
    val parents: List<String?>?,
    @SerializedName("partners")
    val partners: String?,
    @SerializedName("resting_place")
    val restingPlace: String?,
    @SerializedName("spouses")
    val spouses: String?,
    @SerializedName("years")
    val years: String?
) : Parcelable