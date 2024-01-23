package com.baltajmn.gigi.presentation.di

import com.baltajmn.gigi.presentation.screens.details.DetailsViewModel
import com.baltajmn.gigi.presentation.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val PresentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
}