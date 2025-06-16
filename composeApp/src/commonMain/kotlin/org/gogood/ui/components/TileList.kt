package org.gogood.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gogood.data.model.Tileable

@Composable
fun <T : Tileable> GGTileListLarge(
    items: List<T>,
    key: (T) -> String,
    onTileClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items, { key(it) }) { item ->
            GGTileLarge(
                tileable = item,
                onClick = { onTileClick(item) },
            )
        }
    }
}

@Composable
fun <T : Tileable> GGTileListSmall(
    title: String,
    onRowClick: () -> Unit,
    items: List<T>,
    key: (T) -> String,
    onTileClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .clickable { onRowClick() },
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onBackground,
        )

        GGMoreButton({ onRowClick() })
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 10.dp),
    ) {
        items(items, { key(it) }) { item ->
            GGTileSmall(
                tileable = item,
                onClick = { onTileClick(item) },
            )
        }
    }
}
