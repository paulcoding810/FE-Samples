package com.paulcoding.fesamples.core.domain

import com.paulcoding.fesamples.core.domain.repository.HomeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::HomeRepository)
}