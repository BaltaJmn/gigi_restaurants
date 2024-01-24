package com.baltajmn.gigi.core.db.di

import androidx.room.Room
import com.baltajmn.gigi.core.db.datasource.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "pokemon"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().favoritesDao() }
}