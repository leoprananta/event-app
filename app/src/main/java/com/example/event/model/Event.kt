package com.example.event.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event (
                  val name: String?,
                  val image: String?,
                  val location: String?,
                  val htm: String?,
                  val kuota: String?,
                  val date: String?,
                  val by: String?,
                  val desc: String?
):Parcelable {
    companion object {
        const val FAVORITE_EVENT: String = "FAVORITE_EVENT"
        const val TERDAFTAR_EVENT: String = "TERDAFTAR_EVENT"
        const val TAMBAH_EVENT: String = "TAMBAH_EVENT"
        const val TOP_EVENT : String = "TOP_EVENT"
        const val NAME: String = "NAME"
        const val POSTER: String = "POSTER"
        const val LOCATION: String = "LOCATION"
        const val HTM: String = "HTM"
        const val KUOTA: String = "KUOTA"
        const val DATE: String = "DATE"
        const val BY: String = "BY"
        const val DESC: String = "DESC"
    }
}