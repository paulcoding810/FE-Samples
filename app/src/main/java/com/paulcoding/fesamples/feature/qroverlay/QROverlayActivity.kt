package com.paulcoding.fesamples.feature.qroverlay

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.android.ext.android.inject

class QROverlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: QROverlayViewModel by inject()

        setContent {
            val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()
            Scaffold(
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
            ) {
                ScanScreen(
                    modifier = Modifier.padding(it),
                    surfaceRequest = surfaceRequest,
                    onPreview = viewModel::startPreview,
                    onClose = {
                        finishAfterTransition()
                    }
                )
            }
        }
    }
}