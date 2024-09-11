package com.atharok.btremote.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R

@Composable
fun DefaultElevatedCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
    elevation: Dp = dimensionResource(id = R.dimen.elevation),
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = shape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
    ) {
        content()
    }
}

@Composable
fun CircleElevatedCard(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation),
    content: @Composable () -> Unit
) {
    DefaultElevatedCard(
        modifier = modifier,
        shape = shape,
        elevation = elevation,
        content = content
    )
}

/*@Composable
fun FilledCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        content()
    }
}*/

@Composable
fun CardsLayout(
    currentIndex: Int,
    lastIndex: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    DefaultElevatedCard(
        modifier = modifier,
        shape = when {
            currentIndex == 0 && lastIndex - 1 == 0 -> RoundedCornerShape(
                size = dimensionResource(id = R.dimen.card_corner_radius)
            )
            currentIndex == 0 -> RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.card_corner_radius),
                topEnd = dimensionResource(id = R.dimen.card_corner_radius)
            )
            currentIndex == lastIndex-1 -> RoundedCornerShape(
                bottomStart = dimensionResource(id = R.dimen.card_corner_radius),
                bottomEnd = dimensionResource(id = R.dimen.card_corner_radius)
            )
            else -> RectangleShape
        }
    ) {
        content()
    }
}