package com.paulcoding.fesamples.feature.qroverlay

import android.Manifest
import android.widget.Toast
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.SurfaceRequest
import androidx.camera.viewfinder.core.ImplementationMode
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.paulcoding.fesamples.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanScreen(
    surfaceRequest: SurfaceRequest?,
    modifier: Modifier = Modifier,
    onPreview: (LifecycleOwner) -> Unit,
    onClose: () -> Unit,
) {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        cameraPermissionState.launchPermissionRequest()
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        when {
            cameraPermissionState.status.isGranted -> {
                CameraContent(surfaceRequest, onPreview)
            }

            cameraPermissionState.status is PermissionStatus.Denied -> {
                if ((cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                    Toast.makeText(context, "Camera permission is required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun CameraContent(surfaceRequest: SurfaceRequest?, startPreview: (LifecycleOwner) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        startPreview(lifecycleOwner)
    }

    surfaceRequest?.let {

        Box {
            CameraXViewfinder(
                modifier = Modifier.fillMaxSize(),
                implementationMode = ImplementationMode.EXTERNAL,
                surfaceRequest = surfaceRequest,
            )
            //Overlay()
            Overlay2(
                Color.Black.copy(alpha = 0.1f),
                250.dp
            )
        }
    }
}
