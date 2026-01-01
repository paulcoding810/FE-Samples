package com.paulcoding.fesamples

import com.paulcoding.fesamples.feature.home.HomesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomesViewModel)
}