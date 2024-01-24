package com.baltajmn.gigi.domain.model

import com.baltajmn.gigi.core.network.model.Error

data class Location(
    val locationId: Int,
    val name: String,
    val distance: String,
    val rating: String?,
    val bearing: String,
    val addressObj: Address
)

data class Address(
    val street1: String,
    val street2: String,
    val city: String,
    val state: String,
    val country: String,
    val postalcode: String,
    val addressString: String,
    val phone: String,
    val latitude: Double,
    val longitude: Double,
    val error: Error?
)