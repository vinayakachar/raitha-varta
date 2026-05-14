package com.developer.raitha_varta.presentation.screens.successscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developer.raitha_varta.data.SuccessStoryEntity
import com.developer.raitha_varta.ui.theme.ForestGreen
import kotlinx.coroutines.launch

@Composable
fun SuccessStoryPagerScreen(stories: List<SuccessStoryEntity>) {
    val pagerState = rememberPagerState(pageCount = { stories.size })
    val scope = rememberCoroutineScope()

    if (stories.isEmpty()) {
        // Loading or Empty State
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = ForestGreen)
        }
    } else {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            pageSpacing = 16.dp
        ) { page ->
            SuccessStoryCard(
                story = stories[page],
                onNextClick = {
                    if (pagerState.currentPage < stories.size - 1) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    }
                }
            )
        }
    }
}
