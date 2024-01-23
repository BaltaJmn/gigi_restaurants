package com.baltajmn.gigi.data.di

import com.baltajmn.gigi.data.DefaultGigiRepository
import com.baltajmn.gigi.data.GigiRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DataModule = module {
    singleOf(::DefaultGigiRepository) bind GigiRepository::class
}