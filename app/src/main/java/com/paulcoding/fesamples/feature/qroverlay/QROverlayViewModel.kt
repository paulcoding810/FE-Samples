package com.paulcoding.fesamples.feature.qroverlay

import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCaseGroup
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QROverlayViewModel(
    private val cameraProviderManager: CameraProviderManager
) : ViewModel() {
    private val aspectRatioStrategy =
        AspectRatioStrategy(AspectRatio.RATIO_16_9, AspectRatioStrategy.FALLBACK_RULE_NONE)

    private val resolutionSelector = ResolutionSelector.Builder()
        .setAspectRatioStrategy(aspectRatioStrategy)
        .build()

    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    // Camera use cases
    private val previewUseCase by lazy {
        androidx.camera.core.Preview.Builder()
            .setResolutionSelector(resolutionSelector)
            .build().apply {
                setSurfaceProvider { newSurfaceRequest ->
                    _surfaceRequest.update { newSurfaceRequest }
                }
            }
    }

    /**
     * Initialize and start camera preview with QR code scanning.
     */
    fun startPreview(lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            try {
                println("Starting camera preview...")
                bindCameraToLifecycle(lifecycleOwner)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Bind camera use cases to lifecycle.
     */
    private suspend fun bindCameraToLifecycle(lifecycleOwner: LifecycleOwner) {
        val cameraProvider = cameraProviderManager.getCameraProvider()
        val useCaseGroup = UseCaseGroup.Builder()
            .addUseCase(previewUseCase)
            .build()

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            CameraSelector.DEFAULT_BACK_CAMERA,
            useCaseGroup
        )
    }
}