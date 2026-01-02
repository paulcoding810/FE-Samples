package com.paulcoding.fesamples.feature.qroverlay

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.paulcoding.fesamples.R


@Composable
fun Overlay2(
    backgroundColor: Color = Color.Black.copy(alpha = 0.5f),
    squareSize: Dp = 250.dp,
) {
    val topLeftPainter = painterResource(R.drawable.top_left)
    val topRightPainter = painterResource(R.drawable.top_right)
    val bottomLeftPainter = painterResource(R.drawable.bottom_left)
    val bottomRightPainter = painterResource(R.drawable.bottom_right)


    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
    ) {
        // Draw background
        drawRect(color = backgroundColor)

        val squarePx = squareSize.toPx()
        val iconPx = 64.dp.toPx()
        val iconTranslatePx = 3.dp.toPx()
        val left = (size.width - squarePx) / 2f
        val top = (size.height - squarePx) / 2f
        val right = left + squarePx
        val bottom = top + squarePx

        val topLeft = Offset(left, top)

        drawRoundRect(
            color = Color.Transparent,
            topLeft = topLeft,
            //cornerRadius = CornerRadius(16.dp.toPx()),
            size = Size(squarePx, squarePx),
            blendMode = BlendMode.Clear
        )
        translate(left - iconTranslatePx, top - iconTranslatePx) {
            with(topLeftPainter) {
                draw(
                    size = Size(64.dp.toPx(), 64.dp.toPx()),
                    alpha = 1f,
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }
        translate(right - iconPx + iconTranslatePx, top - iconTranslatePx) {
            with(topRightPainter) {
                draw(
                    size = Size(64.dp.toPx(), 64.dp.toPx()),
                    alpha = 1f,
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }
        translate(left - iconTranslatePx, bottom - iconPx + iconTranslatePx) {
            with(bottomLeftPainter) {
                draw(
                    size = Size(64.dp.toPx(), 64.dp.toPx()),
                    alpha = 1f,
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }
        translate(right - iconPx + iconTranslatePx, bottom - iconPx + iconTranslatePx) {
            with(bottomRightPainter) {
                draw(
                    size = Size(64.dp.toPx(), 64.dp.toPx()),
                    alpha = 1f,
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewOverlay2() {
    Overlay2()
}
