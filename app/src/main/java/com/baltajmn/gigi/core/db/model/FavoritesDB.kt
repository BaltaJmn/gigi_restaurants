package com.baltajmn.gigi.core.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class FavoritesDB(
    @PrimaryKey
    val id: Int,
    val name:String
)