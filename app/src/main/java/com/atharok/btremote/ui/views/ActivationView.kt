package com.atharok.btremote.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.MaterialButton
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.TextMedium
import com.atharok.btremote.ui.components.TextNormalSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivationView(
    topBarTitle: String,
    image: ImageVector,
    title: String,
    message: String,
    buttonIcon: ImageVector,
    buttonText: String,
    buttonOnClick: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = topBarTitle,
        modifier = modifier,
        scrollBehavior = null,
        topBarActions = {
            SettingsAction(openSettings)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_large),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier)

                Content(
                    image = image,
                    title = title,
                    message = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( // Permet de centrer verticalement, indépendamment du inner Padding.
                            top = innerPadding.calculateBottomPadding() / 2f,
                            bottom = innerPadding.calculateTopPadding() / 2f
                        )
                )

                MaterialButton(
                    onClick = buttonOnClick,
                    modifier = Modifier.fillMaxWidth(),
                    text = buttonText,
                    icon = buttonIcon,
                )
            }
        }
    }
}

@Composable
private fun Content(
    image: ImageVector,
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .aspectRatio(1f),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )

        TextMedium(
            text = title,
            Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_standard)),
            textAlign = TextAlign.Center
        )

        TextNormalSecondary(
            text = message,
            textAlign = TextAlign.Center
        )
    }
}