package com.example.testmobileca.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class Account(
    @Json(name = "balance") var balance: Double,
    @Json(name = "contract_number") var contract_number: String,
    @Json(name = "holder") var holder: String,
    @Json(name = "id") var id: String,
    @Json(name = "label") var label: String,
    @Json(name = "operations") var operations: List<Operation>,
    @Json(name = "order") var order: Int,
    @Json(name = "product_code") var product_code: String,
    @Json(name = "role") var role: Int,
) : Parcelable