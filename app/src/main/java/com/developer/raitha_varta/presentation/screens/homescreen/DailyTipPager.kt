package com.developer.raitha_varta.presentation.screens.homescreen

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.developer.raitha_varta.R
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

    val primaryTitle = if (isKannada) tip.titleKn else tip.titleEn
    val secondaryTitle = if (isKannada) tip.titleEn else tip.titleKn
    val displayDescription = if (isKannada) tip.descKn else tip.descEn

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // TOP SECTION: Image (40% height)
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
                    transition = CrossFade
                )

                StoryIndicator(segments = total, currentIndex = index)

                // FLOATING NAVIGATION ON IMAGE
                // Back Button (Bottom Left, above Category)
                if (index > 0) {
                    Surface(
                        onClick = onBackClick,
                        shape = CircleShape,
                        color = Color.Black.copy(alpha = 0.35f), // Darker for visibility on image
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 12.dp, bottom = 48.dp) // Padded above the date line
                            .size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }

                // Next Button (Bottom Right, above Category)
                Surface(
                    onClick = onNextClick,
                    shape = CircleShape,
                    color = Color.Black.copy(alpha = 0.35f),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 12.dp, bottom = 48.dp) // Padded above the date line
                        .size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                // Urgent Tag
                if (tip.isUrgent) {
                    Surface(
                        color = Color.Red,
                        shape = CircleShape,
                        modifier = Modifier.padding(12.dp).align(Alignment.TopEnd).padding(top = 16.dp)
                    ) {
                        Text(
                            text = if (isKannada) "ತುರ್ತು" else "Urgent",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                // Category & Date Row
                Row(
                    modifier = Modifier.align(Alignment.BottomStart).padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🌱 ${tip.category}  •  📅 ಮೇ 2, 2026", color = Color.White, fontSize = 12.sp)
                }
            }

            // BOTTOM SECTION: Content (60% height)
            Column(modifier = Modifier.padding(16.dp).weight(0.6f)) {

                // Title Section (Now has the full width with no buttons!)
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = primaryTitle,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 2,
                        lineHeight = 28.sp
                    )
                    Text(
                        text = secondaryTitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Centered Description Box
                Surface(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    color = ForestGreen,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = displayDescription,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 26.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.swipe_hint),
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
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp, start = 12.dp, end = 12.dp),
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
