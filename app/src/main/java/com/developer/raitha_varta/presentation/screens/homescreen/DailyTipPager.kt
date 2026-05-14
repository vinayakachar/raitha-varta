package com.developer.raitha_varta.presentation.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.developer.raitha_varta.data.TipEntity
import com.developer.raitha_varta.ui.theme.ForestGreen

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DailyTipPager(
    tip: TipEntity,
    index: Int,
    total: Int,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isKannada = configuration.locales[0].language == "kn"
    val displayTitle = if (isKannada) tip.titleKn else tip.titleEn

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
            ) {
                GlideImage(
                    model = tip.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    transition = CrossFade,
                    requestBuilderTransform = {
                        it.signature(com.bumptech.glide.signature.ObjectKey(tip.imageUrl))
                            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                    }
                )

                StoryIndicator(segments = total, currentIndex = index)

                if (tip.isUrgent) {
                    Surface(
                        color = Color.Red,
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.TopEnd)
                            .padding(top = 16.dp) // Offset to avoid overlapping StoryIndicator
                    ) {
                        Text(
                            text = if (isKannada) "ತುರ್ತು" else "Urgent",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🌱 ${tip.category}  •  📅 ಮೇ 2, 2026", color = Color.White, fontSize = 12.sp)
                }
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.6f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (index > 0) {
                        Surface(
                            onClick = onBackClick,
                            shape = CircleShape,
                            color = Color(0xFFF1FDF6),
                            shadowElevation = 4.dp,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = "Previous",
                                    tint = ForestGreen
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = displayTitle,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = if (isKannada) tip.titleEn else tip.titleKn,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Surface(
                        onClick = onNextClick,
                        shape = CircleShape,
                        color = Color(0xFFF1FDF6),
                        shadowElevation = 4.dp,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Next",
                                tint = ForestGreen
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false),
                    shape = RoundedCornerShape(16.dp),
                    color = ForestGreen,
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = tip.descKn,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = tip.descEn,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f),
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "← Swipe for more instructions",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun StoryIndicator(segments: Int, currentIndex: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(segments) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == currentIndex) Color(0xFF00A341)
                        else Color.White.copy(alpha = 0.4f)
                    )
            )
        }
    }
}
