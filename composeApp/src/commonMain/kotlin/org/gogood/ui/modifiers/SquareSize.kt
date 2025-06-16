package org.gogood.ui.modifiers

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import kotlin.math.max
import kotlin.math.min

@Stable
fun Modifier.squareSize(position: Float = 0.5f): Modifier =
    layout { measurable, constraints ->
        val constraintsSize = min(constraints.maxWidth, constraints.maxHeight)
        val squareConstraints =
            constraints.copy(
                maxWidth = constraintsSize,
                maxHeight = constraintsSize,
            )

        val placeable = measurable.measure(squareConstraints)
        val size = max(placeable.width, placeable.height)

        layout(width = size, height = size) {
            val x = ((size - placeable.width) * position).toInt()
            val y = ((size - placeable.height) * position).toInt()
            placeable.placeRelative(x, y)
        }
    }
