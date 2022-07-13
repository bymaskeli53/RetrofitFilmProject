package com.example.retrofitfilmproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val director: String,
    val release_date: String
): Parcelable{
    override fun toString(): String {
        return title
    }
}



