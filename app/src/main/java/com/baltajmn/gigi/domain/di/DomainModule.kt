package com.baltajmn.gigi.domain.di

import com.baltajmn.gigi.domain.GetNearbyLocations
import com.baltajmn.gigi.domain.GetNearbyLocationsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DomainModule = module {
    factoryOf(::GetNearbyLocations) bind GetNearbyLocationsUseCase::class
}