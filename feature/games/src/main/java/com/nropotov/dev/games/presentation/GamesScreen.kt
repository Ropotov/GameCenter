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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

    val state by viewModel.screenState.collectAsState()

    when (val state: GamesUiState = state) {
        GamesUiState.Error -> ErrorState()
        GamesUiState.Loading -> LoadingState()
        is GamesUiState.Success -> GamesScreen(state.pcGames, state.ps5Games)
    }
}

@Composable
fun ErrorState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_oops),
            contentDescription = stringResource(R.string.not_available)
        )
        Text(
            modifier = Modifier.padding(GameCenterTheme.dimens.dp36),
            text = stringResource(R.string.error_message)
        )
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = GameCenterTheme.colors.primaryTextColor,
            modifier = Modifier
                .size(GameCenterTheme.dimens.dp40)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun GamesScreen(
    pcGames: List<UiGamesModel>,
    ps5Games: List<UiGamesModel>,
) {

    val lazyScrollState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GameCenterTheme.colors.primaryBackgroundColor),
        state = lazyScrollState
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(GameCenterTheme.dimens.dp24)
            ) {
                Text(
                    text = "Game Center",
                    color = GameCenterTheme.colors.primaryTextColor,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        item {
            GamesBlock(
                title = stringResource(R.string.pc_games_title),
                subtitle = stringResource(R.string.pc_games_subtitle),
                items = pcGames
            ) {

            }
        }

        item {
            GamesBlock(
                title = stringResource(R.string.ps5_games_title),
                subtitle = stringResource(R.string.ps5_games_subtitle),
                items = ps5Games
            ) {

            }
        }
    }
    CollapsibleToolbar(
        title = stringResource(R.string.game_center),
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
            contentPadding = PaddingValues(horizontal = GameCenterTheme.dimens.dp12),
            horizontalArrangement = Arrangement.spacedBy(GameCenterTheme.dimens.dp8)
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
            contentDescription = stringResource(R.string.game_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(elementSize)
                .clip(MaterialTheme.shapes.extraLarge)
                .border(
                    BorderStroke(width = GameCenterTheme.dimens.dp1, color = Color.Black),
                    shape = MaterialTheme.shapes.extraLarge
                )
        )
        Text(
            text = game.name,
            color = GameCenterTheme.colors.primaryTextColor,
            style = MaterialTheme.typography.bodyLarge,
            minLines = 2,
            maxLines = 2,
            modifier = Modifier
                .width(elementSize)
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
            .padding(GameCenterTheme.dimens.dp12),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                color = GameCenterTheme.colors.primaryTextColor,
                style = MaterialTheme.typography.titleLarge
            )
            subtitle?.let {
                Text(
                    text = subtitle,
                    color = GameCenterTheme.colors.secondaryTextColor,
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
    val dimens = GameCenterTheme.dimens
    val elementWidthSize =
        (screenWidth - dimens.dp12 - dimens.dp8 * FULL_VISIBLE_ITEMS_COUNT - dimens.dp20) / FULL_VISIBLE_ITEMS_COUNT
    val elementHeightSize = (screenHeight - dimens.dp12) / FULL_VISIBLE_ITEMS_COUNT
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
