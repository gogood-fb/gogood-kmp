package org.gogood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.ico_back
import gogood.composeapp.generated.resources.ico_context
import gogood.composeapp.generated.resources.ico_more
import org.gogood.data.model.Action
import org.gogood.ui.theme.extendedColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GGCategoryButton(
    iconRes: DrawableResource,
    labelRes: StringResource,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalContentColor provides
            if (isSelected) {
                MaterialTheme.colorScheme.onBackground
            } else {
                MaterialTheme.extendedColors.textMuted
            },
    ) {
        Column(
            modifier =
                modifier
                    .semantics { role = Role.Button }
                    .heightIn(64.dp)
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .padding(6.dp)
                    .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(3.dp))

            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = stringResource(labelRes),
                modifier =
                    Modifier
                        .padding(horizontal = 6.dp)
                        .widthIn(70.dp),
                fontSize = 12.sp,
                lineHeight = 13.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )

            if (isSelected) {
                Spacer(Modifier.height(3.dp))

                HorizontalDivider(
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(50)),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                )
            } else {
                Spacer(Modifier.height(5.dp))
            }
        }
    }
}

@Composable
fun GGBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    IconButton(onClick, modifier) {
        Icon(
            painter = painterResource(Res.drawable.ico_back),
            contentDescription = null,
            tint = tint,
        )
    }
}

@Composable
fun GGContextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick, modifier) {
        Icon(
            painter = painterResource(Res.drawable.ico_context),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun GGActionButton(
    action: Action,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .semantics { role = Role.Button }
                .heightIn(64.dp)
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(3.dp))

        Box(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.secondary.copy(0.15f), CircleShape)
                    .size(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(action.iconRes),
                contentDescription = null,
                modifier = Modifier,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(action.labelRes),
            modifier =
                Modifier
                    .widthIn(70.dp),
            fontSize = 10.sp,
            lineHeight = 13.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun GGMoreButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick, modifier) {
        Icon(
            painterResource(Res.drawable.ico_more),
            null,
            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun GGTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick) {
        Text(
            text,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
        )
    }
}
