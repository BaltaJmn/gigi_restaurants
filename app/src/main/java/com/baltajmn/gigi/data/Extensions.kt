package com.baltajmn.gigi.data

import com.baltajmn.gigi.core.common.showDistance
import com.baltajmn.gigi.core.db.model.FavoritesDB
import com.baltajmn.gigi.core.network.model.AddressObj
import com.baltajmn.gigi.core.network.model.DetailsApi
import com.baltajmn.gigi.core.network.model.LocationApi
import com.baltajmn.gigi.domain.model.Address
import com.baltajmn.gigi.domain.model.Details
import com.baltajmn.gigi.domain.model.Favorites
import com.baltajmn.gigi.domain.model.Location

fun LocationApi.toDomain() = Location(
    locationId = locationId,
    name = name ?: "",
    distance = distance?.showDistance() ?: "",
    rating = rating,
    bearing = bearing ?: "",
    addressObj = addressObj.toDomain()
)

fun AddressObj.toDomain() = Address(
    street1 = street1 ?: "",
    street2 = street2 ?: "",
    city = city ?: "",
    state = state ?: "",
    country = country ?: "",
    postalcode = postalcode ?: "",
    addressString = addressString ?: "",
    phone = phone ?: "",
    latitude = latitude,
    longitude = longitude,
    error = error
)

fun FavoritesDB.toDomain() = Favorites(
    id = id,
    name = name
)

fun DetailsApi.toDomain() = Details(
    name = name,
    description = description ?: "",
    email = email ?: "",
    phone = phone ?: "",
    rating = rating.toString(),
    ratingUrl = ratingImageUrl ?: ""
)
