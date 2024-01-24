package com.baltajmn.gigi.core.network.model

import com.google.gson.annotations.SerializedName

data class DetailsApi(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("rating")
    val rating: Float?,
    @SerializedName("rating_image_url")
    val ratingImageUrl: String?
)