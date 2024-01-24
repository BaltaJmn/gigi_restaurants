package com.baltajmn.gigi.presentation.di

import com.baltajmn.gigi.presentation.screens.details.DetailsViewModel
import com.baltajmn.gigi.presentation.screens.home.HomeViewModel
import com.baltajmn.gigi.presentation.screens.favorites.FavoritesViewModel

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val PresentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::DetailsViewModel)
}