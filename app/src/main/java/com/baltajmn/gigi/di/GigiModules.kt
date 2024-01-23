package com.baltajmn.gigi.di

import com.baltajmn.gigi.core.network.di.NetworkModule
import com.baltajmn.gigi.data.di.DataModule
import com.baltajmn.gigi.domain.di.DomainModule
import com.baltajmn.gigi.presentation.di.PresentationModule
import org.koin.core.module.Module
import org.koin.dsl.module

val GigiModules: Module
    get() = module {
        includes(
            listOf(
                NetworkModule,
                DataModule,
                DomainModule,
                PresentationModule
            )
        )
    }