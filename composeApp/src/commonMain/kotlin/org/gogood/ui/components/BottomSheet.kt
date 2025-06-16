package org.gogood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGBottomSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    showHandle: Boolean = true,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    ),
    content: @Composable ColumnScope.() -> Unit,
) {
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = sheetState,
            modifier = modifier
                .consumeWindowInsets(WindowInsets.statusBars)
                .padding(top = topPadding),
            dragHandle = {
                if (showHandle) {
                    Box(
                        Modifier
                            .padding(top = 8.dp)
                            .size(36.dp, 5.dp)
                            .background(
                                Color(0x4d3c3c43),
                                RoundedCornerShape(50),
                            ),
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = { WindowInsets(0.dp) },
        ) {
            content.invoke(this)
        }
    }
}