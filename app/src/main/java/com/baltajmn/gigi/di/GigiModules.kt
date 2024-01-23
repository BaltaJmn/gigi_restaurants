package com.baltajmn.gigi.di

import org.koin.core.module.Module
import org.koin.dsl.module

val GigiModules: Module
    get() = module {
        includes(
            listOf(

            )
        )
    }