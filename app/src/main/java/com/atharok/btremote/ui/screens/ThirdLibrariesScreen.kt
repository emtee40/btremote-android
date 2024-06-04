package com.atharok.btremote.ui.screens

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.ThirdLibrary
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.DefaultElevatedCard
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.TextMedium
import com.atharok.btremote.ui.components.TextNormalLink
import com.atharok.btremote.ui.components.TextNormalSecondary

@Composable
fun ThirdLibrariesScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatelessThirdLibrariesScreen(
        libraries = ThirdLibrary.entries,
        navigateUp = navigateUp,
        modifier = modifier
    )
}

@Composable
fun StatelessThirdLibrariesScreen(
    libraries : List<ThirdLibrary>,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.third_party_library),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier,
            contentPadding = innerPadding
        ) {
            items(libraries) { item ->
                ThirdLibraryItem(
                    library = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_standard),
                            vertical = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        }
    }
}

@Composable
private fun ThirdLibraryItem(
    library: ThirdLibrary,
    modifier: Modifier = Modifier,
) {
    DefaultElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            TextMedium(text = stringResource(id = library.title))
            TextNormalSecondary(text = stringResource(id = library.id))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_small)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_standard)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableLink(
                    textId = library.codeHost,
                    urlId = library.codeUrl
                )

                TextNormalSecondary(text = "|")

                ClickableLink(
                    textId = library.license,
                    urlId = library.licenseUrl
                )
            }
        }
    }
}

@Composable
private fun ClickableLink(
    @StringRes textId: Int,
    @StringRes urlId: Int,
    context: Context = LocalContext.current,
    uriHandler: UriHandler = LocalUriHandler.current,
) {
    TextNormalLink(
        text = stringResource(id = textId),
        modifier = Modifier.clickable {
            uriHandler.openUri(context.getString(urlId))
        }
    )
}