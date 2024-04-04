package com.nropotov.dev.games.presentation

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nropotov.dev.common.components.CollapsibleToolbar
import com.nropotov.dev.common.theme.GameCenterTheme
import com.nropotov.dev.games.R
import com.nropotov.dev.games.domain.models.UiGamesModel

private const val FULL_VISIBLE_ITEMS_COUNT = 2

@Composable
fun GamesListScreen(viewModelFactory: ViewModelProvider.Factory) {
    GamesListScreen(
        viewModel = viewModel(factory = viewModelFactory)
    )
}

@Composable
internal fun GamesListScreen(
    viewModel: GamesScreenViewModel
) {

    val games by viewModel.games.collectAsState()
    val lazyScrollState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        state = lazyScrollState
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Game Center",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        item {
            GamesBlock(
                title = "Games",
                subtitle = "MoreGames",
                items = games,
                onClickTitleBlock = { },
                onClickItem = { }
            )
        }
        item {
            GamesBlock(
                title = "Games",
                subtitle = "MoreGames",
                items = games,
                onClickTitleBlock = { },
                onClickItem = { }
            )
        }
        item {
            GamesBlock(
                title = "Games",
                items = games,
                onClickItem = { }
            )
        }
    }
    CollapsibleToolbar(
        title = "Game Center",
        lazyScrollState = lazyScrollState,
    )
}

@Composable
fun GamesBlock(
    title: String,
    subtitle: String? = null,
    items: List<UiGamesModel>,
    onClickTitleBlock: (() -> Unit)? = null,
    onClickItem: (() -> Unit)
) {
    Column(modifier = Modifier.wrapContentHeight()) {
        TitleBlock(
            title = title,
            subtitle = subtitle,
            onClickTitleBlock
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = items) {
                PageCardItem(
                    game = it,
                    elementSize = getElementSize(),
                    onClick = onClickItem
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PageCardItem(
    game: UiGamesModel,
    elementSize: Dp,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        GlideImage(
            model = game.imageUrl,
            contentDescription = "Game image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(elementSize)
                .clip(MaterialTheme.shapes.extraLarge)
                .border(
                    BorderStroke(width = 1.dp, color = Color.Black),
                    shape = MaterialTheme.shapes.extraLarge
                )
        )
        Text(
            text = game.name,
            fontSize = 12.sp,
            maxLines = 2,
            minLines = 2,
            modifier = Modifier
                .wrapContentHeight()
        )
    }
}

@Composable
fun TitleBlock(
    title: String,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )
            subtitle?.let {
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        if (onClick != null) Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.going_inside_the_block_description)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleBlockPreview() {
    GameCenterTheme {
        TitleBlock(title = "Games", subtitle = "More games") {}
    }
}

@Composable
private fun getElementSize(): Dp {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val elementWidthSize =
        (screenWidth - 12.dp - 8.dp * FULL_VISIBLE_ITEMS_COUNT - 20.dp) / FULL_VISIBLE_ITEMS_COUNT
    val elementHeightSize = (screenHeight - 12.dp) / FULL_VISIBLE_ITEMS_COUNT
    val elementSize by remember {
        mutableStateOf(
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> elementHeightSize
                else -> elementWidthSize
            }
        )
    }
    return elementSize
}
