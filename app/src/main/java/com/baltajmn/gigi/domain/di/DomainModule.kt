package com.baltajmn.gigi.domain.di

import com.baltajmn.gigi.domain.AddToFavorites
import com.baltajmn.gigi.domain.AddToFavoritesUseCase
import com.baltajmn.gigi.domain.GetDetails
import com.baltajmn.gigi.domain.GetDetailsUseCase
import com.baltajmn.gigi.domain.GetFavoritesList
import com.baltajmn.gigi.domain.GetFavoritesListUseCase
import com.baltajmn.gigi.domain.GetLocations
import com.baltajmn.gigi.domain.GetLocationsUseCase
import com.baltajmn.gigi.domain.GetNearbyLocations
import com.baltajmn.gigi.domain.GetNearbyLocationsUseCase
import com.baltajmn.gigi.domain.RemoveFavorite
import com.baltajmn.gigi.domain.RemoveFavoriteUseCase
import com.baltajmn.gigi.domain.UpdateFavoritesList
import com.baltajmn.gigi.domain.UpdateFavoritesListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DomainModule = module {
    factoryOf(::GetLocations) bind GetLocationsUseCase::class
    factoryOf(::GetNearbyLocations) bind GetNearbyLocationsUseCase::class
    factoryOf(::GetFavoritesList) bind GetFavoritesListUseCase::class
    factoryOf(::AddToFavorites) bind AddToFavoritesUseCase::class
    factoryOf(::UpdateFavoritesList) bind UpdateFavoritesListUseCase::class
    factoryOf(::RemoveFavorite) bind RemoveFavoriteUseCase::class
    factoryOf(::GetDetails) bind GetDetailsUseCase::class
}