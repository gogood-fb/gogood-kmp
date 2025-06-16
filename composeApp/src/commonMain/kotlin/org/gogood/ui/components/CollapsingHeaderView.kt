package org.gogood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

private val pinnedHeight = 64.dp
private val expandedHeight = 500.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingHeaderLayout(
    headerBackground: @Composable BoxScope.(collapsedFraction: Float) -> Unit,
    expandedContent: @Composable BoxScope.(expandedFraction: Float) -> Unit,
    collapsedContent: @Composable BoxScope.(collapsedFraction: Float) -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            val pinnedHeightPx: Float
            val maxHeightPx: Float

            LocalDensity.current.run {
                pinnedHeightPx = pinnedHeight.toPx() + WindowInsets.statusBars.getTop(this)
                maxHeightPx = expandedHeight.toPx()
            }

            val offset = maxHeightPx - pinnedHeightPx
            SideEffect {
                if (scrollBehavior.state.heightOffsetLimit != -offset) {
                    scrollBehavior.state.heightOffsetLimit = -offset
                }
            }

            val height = maxHeightPx + scrollBehavior.state.heightOffset
            val fraction = (-scrollBehavior.state.heightOffset / height).coerceIn(0f, 1f)

            // Set up support for resizing the top app bar when vertically dragging the bar itself.
            val appBarDragModifier =
                Modifier.draggable(
                    orientation = Orientation.Vertical,
                    state =
                        rememberDraggableState { delta ->
                            scrollBehavior.state.heightOffset += delta
                        },
                    onDragStopped = { velocity ->
//                        settleAppBar(
//                            scrollBehavior.state,
//                            velocity,
//                            scrollBehavior.flingAnimationSpec,
//                            scrollBehavior.snapAnimationSpec
//                        )
                    },
                )

            LocalDensity.current.run {
                Box(
                    modifier =
                        appBarDragModifier
                            .height(height.toDp()),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(unbounded = true, align = Alignment.Bottom)
                                .height(expandedHeight),
                    ) { headerBackground.invoke(this, 1 - fraction) }

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(unbounded = true, align = Alignment.Bottom)
                            .height(expandedHeight)
                            .background(
                                Brush.verticalGradient(
                                    0.0f to Color(0x00000000),
                                    1.0f to Color(0x80000000),
                                ),
                            ),
                    ) { expandedContent.invoke(this, 1 - fraction) }

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    0.0f to Color(0x99000000),
                                    1.0f to Color(0x00000000),
                                ),
                            ),
                    ) { collapsedContent.invoke(this, fraction) }
                }
            }
        },
    ) { paddingValues ->
        Box(
            Modifier.fillMaxWidth()
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            content.invoke()
        }
    }
}
