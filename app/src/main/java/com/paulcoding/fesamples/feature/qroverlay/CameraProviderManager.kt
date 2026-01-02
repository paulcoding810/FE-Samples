package com.paulcoding.fesamples.feature.qroverlay

import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance

interface CameraProviderManager {
    suspend fun getCameraProvider(): ProcessCameraProvider
}

class CameraXProcessCameraProviderManager(
    private val context: Context
) : CameraProviderManager {
    override suspend fun getCameraProvider() = ProcessCameraProvider.awaitInstance(context)
}