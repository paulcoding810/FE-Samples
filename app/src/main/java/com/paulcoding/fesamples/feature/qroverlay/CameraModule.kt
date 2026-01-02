package com.paulcoding.fesamples.feature.qroverlay

import org.koin.dsl.module

val cameraModule = module {
    single<CameraProviderManager> {
        CameraXProcessCameraProviderManager(get())
    }
}