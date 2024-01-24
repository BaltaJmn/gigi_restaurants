package com.baltajmn.gigi.core.network.model

import com.google.gson.annotations.SerializedName

data class ResponseLocationApi(
    val data: List<LocationApi>
)

data class LocationApi(
    @SerializedName("location_id")
    val locationId: Int,
    val name: String? = "",
    val distance: String? = "",
    val rating: String? = "",
    val bearing: String? = "",
    @SerializedName("address_obj")
    val addressObj: AddressObj
)

data class AddressObj(
    val street1: String? = "",
    val street2: String? = "",
    val city: String? = "",
    val state: String? = "",
    val country: String? = "",
    val postalcode: String? = "",
    @SerializedName("address_string")
    val addressString: String? = "",
    val phone: String? = "",
    val latitude: Double,
    val longitude: Double,
    val error: Error?
)

data class Error(
    val message: String? = "",
    val type: String? = "",
    val code: Int
)