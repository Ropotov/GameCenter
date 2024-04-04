package com.nropotov.dev.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private const val TOOLBAR_ANIMATION_DURATION = 250
private const val COLLAPSED_TOOLBAR_ID = "collapsed_toolbar"

@Composable
fun CollapsibleToolbar(
    title: String,
    lazyScrollState: LazyListState,
    onNavigateUp: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val isItemIndexZeroOrLess by remember {
        derivedStateOf { lazyScrollState.firstVisibleItemScrollOffset <= 0 }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = !isItemIndexZeroOrLess,
        exit = slideOutVertically(animationSpec = keyframes {
            durationMillis = TOOLBAR_ANIMATION_DURATION
        }
        ),
        enter = slideInVertically(animationSpec = keyframes {
            durationMillis = TOOLBAR_ANIMATION_DURATION
        }
        )
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .statusBarsPadding()
                .padding(
                    top = 14.dp,
                    bottom = 14.dp
                )
                .padding(horizontal = 12.dp)
                .layoutId(COLLAPSED_TOOLBAR_ID)
                .fillMaxWidth()
        ) {
            if (onNavigateUp != null) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterStart)
                        .clickable(onClick = onNavigateUp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}