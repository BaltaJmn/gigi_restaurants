package com.baltajmn.gigi.core.network.di

import com.baltajmn.gigi.core.network.client.HttpClient
import com.baltajmn.gigi.core.network.datasource.DefaultNetworkGigiDatasource
import com.baltajmn.gigi.core.network.datasource.NetworkGigiDatasource
import com.baltajmn.gigi.core.network.interceptors.TokenInterceptor
import com.baltajmn.gigi.core.network.service.GigiService
import okhttp3.Interceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private const val BASE_URL: String = "https://api.content.tripadvisor.com"

val NetworkModule = module {
    factoryOf(::TokenInterceptor) bind Interceptor::class

    single(named("Client")) {
        HttpClient.getClient(
            baseUrl = BASE_URL,
            interceptors = listOf(
                get<TokenInterceptor>()
            )
        )
    }

    single { GigiService(get(named("Client"))) }
    singleOf(::DefaultNetworkGigiDatasource) bind NetworkGigiDatasource::class
}