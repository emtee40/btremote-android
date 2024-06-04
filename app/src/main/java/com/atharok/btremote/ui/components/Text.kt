package com.atharok.btremote.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.atharok.btremote.ui.theme.Typography

@Composable
fun TextRemoteNumber(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(fontSize = fontSize),
        fontWeight = FontWeight.SemiBold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}


// ---- Large ----

@Composable
fun TextLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = 1,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        style = Typography.titleLarge,
        fontWeight = FontWeight.Medium,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

// ---- Medium ----

@Composable
fun TextMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = Typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        textAlign = textAlign
    )
}

// ---- Normal ----

@Composable
fun TextNormal(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    style: TextStyle = Typography.titleSmall,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        style = style,
        textDecoration = textDecoration,
        textAlign = textAlign
    )
}

@Composable
fun TextNormalSecondary(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    TextNormal(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = Typography.bodyMedium,
        textAlign = textAlign
    )
}

@Composable
fun TextNormalLink(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    TextNormal(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        textAlign = textAlign
    )
}