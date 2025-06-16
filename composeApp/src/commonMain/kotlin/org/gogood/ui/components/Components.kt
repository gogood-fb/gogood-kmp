package org.gogood.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.category_all
import gogood.composeapp.generated.resources.explore_search
import gogood.composeapp.generated.resources.ico_all
import gogood.composeapp.generated.resources.ico_filter
import gogood.composeapp.generated.resources.ico_search
import gogood.composeapp.generated.resources.ico_sustain_score
import org.gogood.data.model.ContentItem
import org.gogood.ui.modifiers.squareSize
import org.gogood.ui.theme.extendedColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GGCategoryBar(
    selectedType: ContentItem.Type?,
    onCategoryClick: (type: ContentItem.Type?) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier) {
        item {
            GGCategoryButton(
                iconRes = Res.drawable.ico_all,
                labelRes = Res.string.category_all,
                isSelected = selectedType == null,
                onClick = { onCategoryClick(null) },
            )
        }
        items(
            items = ContentItem.Type.entries,
            key = { it },
        ) {
            GGCategoryButton(
                iconRes = it.iconRes,
                labelRes = it.labelRes,
                isSelected = it == selectedType,
                onClick = { onCategoryClick(it) },
            )
        }
    }
}

@Composable
fun GGSustainabilityScoreLarge(
    score: Int,
    modifier: Modifier = Modifier,
) {
    GGSustainabilityScore(
        score = score,
        iconSize = 27.dp,
        fontSize = 17.sp,
        minTextWidth = 32.dp,
        contentPadding =
            PaddingValues(
                start = 4.dp,
                top = 4.dp,
                end = 8.dp,
                bottom = 4.dp,
            ),
        modifier = modifier,
    )
}

@Composable
fun GGSustainabilityScoreSmall(
    score: Int,
    modifier: Modifier = Modifier,
) {
    GGSustainabilityScore(
        score = score,
        iconSize = 18.dp,
        fontSize = 12.sp,
        minTextWidth = 24.dp,
        contentPadding =
            PaddingValues(
                start = 2.dp,
                top = 2.dp,
                end = 4.dp,
                bottom = 2.dp,
            ),
        modifier = modifier,
    )
}

@Composable
private fun GGSustainabilityScore(
    score: Int,
    iconSize: Dp,
    fontSize: TextUnit,
    minTextWidth: Dp,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val color =
        when {
            score > 70 -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.secondary
        }

    Row(
        modifier
            .height(IntrinsicSize.Min)
            .background(color, RoundedCornerShape(50))
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(Res.drawable.ico_sustain_score),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier =
                Modifier
                    .size(iconSize),
        )

        Spacer(Modifier.width(contentPadding.calculateStartPadding(LocalLayoutDirection.current)))

        Text(
            text = score.toString(),
            modifier = Modifier.widthIn(minTextWidth),
            fontSize = fontSize,
            lineHeight = fontSize,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun GGSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    locationName: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = stringResource(Res.string.explore_search, locationName),
                fontSize = 14.sp,
            )
        },
        textStyle = TextStyle(fontSize = 14.sp),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ico_search),
                contentDescription = null,
                tint = MaterialTheme.extendedColors.textMuted,
            )
        },
        trailingIcon = {
            IconButton(
                onClick = onFilterClick,
                modifier =
                    Modifier
                        .padding(horizontal = 5.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.extendedColors.textMuted,
                            shape = CircleShape,
                        ),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ico_filter),
                    contentDescription = null,
                    tint = MaterialTheme.extendedColors.textMuted,
                )
            }
        },
        shape = RoundedCornerShape(50),
        colors =
            TextFieldDefaults.colors(
                focusedPlaceholderColor = MaterialTheme.extendedColors.textMuted,
                unfocusedPlaceholderColor = MaterialTheme.extendedColors.textMuted,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.extendedColors.field,
                unfocusedContainerColor = MaterialTheme.extendedColors.field,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
    )
}

@Composable
fun GGMapBadgeLarge(
    ordinal: Int,
    modifier: Modifier = Modifier,
) {
    GGMapBadge(
        ordinal = ordinal,
        fontSize = 22.sp,
        paddingValues = PaddingValues(0.dp),
        modifier = modifier.sizeIn(44.dp, 44.dp),
    )
}

@Composable
fun GGMapBadgeSmall(
    ordinal: Int,
    modifier: Modifier = Modifier,
) {
    GGMapBadge(
        ordinal = ordinal,
        fontSize = 10.sp,
        paddingValues = PaddingValues(0.dp),
        modifier = modifier.sizeIn(20.dp, 20.dp),
    )
}

@Composable
private fun GGMapBadge(
    ordinal: Int,
    fontSize: TextUnit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.secondary, CircleShape)
                .border(1.dp, Color.White, CircleShape)
                .padding(paddingValues)
                .squareSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = ordinal.toString(),
            fontSize = fontSize,
            lineHeight = fontSize,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Composable
fun GGCarouselIndicator(
    count: Int,
    index: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .background(Color(0xccd9d9d9), RoundedCornerShape(50))
            .padding(vertical = 3.dp, horizontal = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(count) {
            val isSelected = index == it
            val color = if (isSelected) Color.White else Color.Black.copy(0.2f)
            Box(
                Modifier
                    .background(color, CircleShape)
                    .size(8.dp),
            ) { }
        }
    }
}
