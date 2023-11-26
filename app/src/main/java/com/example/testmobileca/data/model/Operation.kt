package com.example.testmobileca.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class Operation (
    @Json(name = "amount") var amount: String,
    @Json(name = "category") var category: String,
    @Json(name = "date") var date: String,
    @Json(name = "id") var id: String,
    @Json(name = "title") var title: String,

    ) : Parcelable


