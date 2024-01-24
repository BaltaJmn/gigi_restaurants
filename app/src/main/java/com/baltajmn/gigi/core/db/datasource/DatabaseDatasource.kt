package com.baltajmn.gigi.core.db.datasource

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.baltajmn.gigi.core.db.model.FavoritesDB

@Database(
    entities = [
        FavoritesDB::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    suspend fun getFavoritesList(): List<FavoritesDB>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoritesList: FavoritesDB)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteFavorite(id: Int)
}