package org.gogood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.ico_location
import org.gogood.data.model.Tileable
import org.jetbrains.compose.resources.painterResource

@Composable
fun GGTileLarge(
    tileable: Tileable,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val data = tileable.toTile()
    GGTile(
        title = data.title,
        subtitle = data.subtitle,
        imageUrl = data.imageUrlLarge,
        score = data.sustainabilityScore,
        imageHeight = 145.dp,
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun GGTileMedium(
    tileable: Tileable,
    modifier: Modifier = Modifier,
) {
    val data = tileable.toTile()

    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Box {
            AsyncImage(
                model = data.imageUrlSmall,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .background(Color.Black.copy(0.2f))
                        .size(116.dp, 98.dp),
            )

            GGSustainabilityScoreSmall(
                data.sustainabilityScore,
                Modifier.align(Alignment.BottomStart)
                    .padding(start = 6.dp, bottom = 6.dp),
            )
        }

        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface,
        ) {
            Column(
                Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = data.title,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    modifier = Modifier.fillMaxWidth(),
                )

                Text(
                    text = data.subtitle,
                    fontSize = 11.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                )

                Text(
                    text = data.description ?: "",
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun GGTileSmall(
    tileable: Tileable,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val data = tileable.toTile()
    GGTile(
        title = data.title,
        subtitle = data.subtitle,
        imageUrl = data.imageUrlSmall,
        score = data.sustainabilityScore,
        imageHeight = 98.dp,
        onClick = onClick,
        modifier = modifier.width(164.dp),
    )
}

@Composable
private fun GGTile(
    title: String,
    subtitle: String,
    imageUrl: String,
    score: Int,
    imageHeight: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() },
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(imageHeight),
        ) {
            val context = LocalPlatformContext.current
            AsyncImage(
                model = ImageRequest.Builder(context).data(imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .background(Color.Black.copy(0.2f))
                        .matchParentSize(),
            )

            GGSustainabilityScoreSmall(
                score,
                Modifier
                    .align(Alignment.BottomEnd)
                    .offset((-10).dp, 9.dp)
                    .zIndex(1f),
            )
        }

        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface,
        ) {
            Column(
                Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 11.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    modifier = Modifier.fillMaxWidth(),
                )

                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun GGDestinationTile(
    tileable: Tileable,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val data = tileable.toTile()
    Column(
        modifier =
            modifier
                .semantics { role = Role.Button }
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White.copy(0.5f))
                .width(126.dp)
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(8.dp))

        Icon(
            painter = painterResource(Res.drawable.ico_location),
            contentDescription = null,
            tint = Color.White,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = data.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(3.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .height(62.dp),
        ) {
            AsyncImage(
                model = data.imageUrlSmall,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .background(Color.Black.copy(0.2f))
                        .matchParentSize(),
            )

            GGSustainabilityScoreSmall(
                data.sustainabilityScore,
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 8.dp, end = 6.dp),
            )
        }
    }
}
