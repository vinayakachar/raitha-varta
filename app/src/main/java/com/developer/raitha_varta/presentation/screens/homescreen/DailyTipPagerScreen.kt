package com.developer.raitha_varta.presentation.screens.homescreen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.raitha_varta.data.TipEntity
import com.developer.raitha_varta.ui.theme.ForestGreen
import kotlinx.coroutines.launch

@Composable
fun DailyTipPagerScreen(tips: List<TipEntity>) {

    val scope = androidx.compose.runtime.rememberCoroutineScope()
    if (tips.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("📭", fontSize = 50.sp)
            Text(
                text = "ಪ್ರಸ್ತುತ ಯಾವುದೇ ಸಲಹೆಗಳಿಲ್ಲ\n(No tips available for this category yet)",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
    } else {
        val pagerState = rememberPagerState(pageCount = { tips.size })
        LaunchedEffect(tips) {
            if (tips.isNotEmpty()) {
                pagerState.scrollToPage(0)
            }
        }


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                pageSpacing = 16.dp,
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) { page ->
                DailyTipPager(
                    tip = tips[page],
                    index = page,
                    total = tips.size,
                    onBackClick = {
                        if (pagerState.currentPage > 0) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1,
                                    animationSpec = spring(stiffness = Spring.StiffnessLow))
                            }
                        }
                    },

                    onNextClick = {
                        if (pagerState.currentPage < tips.size - 1) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1,
                                    animationSpec = spring(stiffness = Spring.StiffnessLow))
                            }
                        }
                    }
                )
            }

            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(tips.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color(0xFF1B5E20) else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }
    }
}
