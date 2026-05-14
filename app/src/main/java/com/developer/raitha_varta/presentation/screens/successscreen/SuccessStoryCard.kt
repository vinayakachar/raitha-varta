package com.developer.raitha_varta.presentation.screens.successscreen

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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.developer.raitha_varta.data.SuccessStoryEntity
import com.developer.raitha_varta.ui.theme.ForestGreen

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SuccessStoryCard(story: SuccessStoryEntity, onNextClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val isKannada = configuration.locales[0].language == "kn"

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7E6)), // Light cream background from screenshot
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            // --- TOP IMAGE SECTION ---
            Box(modifier = Modifier.fillMaxWidth().weight(0.4f)) {
                GlideImage(
                    model = story.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Success Story Badge (Orange)
                Surface(
                    color = Color(0xFFE67E22),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(12.dp).align(Alignment.TopStart)
                ) {
                    Text(
                        text = if (isKannada) "ಯಶಸ್ಸಿನ ಕಥೆ • Success Story" else "Success Story",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // --- CONTENT SECTION ---
            Column(modifier = Modifier.padding(20.dp).weight(0.6f)) {
                // Farmer Info with Icons
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFFD35400), modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(if (isKannada) story.farmerNameKn else story.farmerNameEn, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFD35400), modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(if (isKannada) story.locationKn else story.locationEn, color = Color.Gray, fontSize = 14.sp)
                        }
                    }

                    // Floating Next Button
                    Surface(
                        onClick = onNextClick,
                        shape = CircleShape,
                        color = Color.White,
                        shadowElevation = 4.dp,
                        modifier = Modifier.size(45.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = ForestGreen)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Story White Box
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(if (isKannada) story.storyKn else story.storyEn, fontWeight = FontWeight.Medium, lineHeight = 22.sp)
                    }
                }

                // Yield Increase Green Box
                Surface(
                    color = ForestGreen,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth().height(80.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Using AutoMirrored version of TrendingUp as a standard Material Icon
                        Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = null, tint = Color.White, modifier = Modifier.size(30.dp))
                        Spacer(Modifier.width(12.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(story.yieldIncrease, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                            Text(
                                if (isKannada) "ಉತ್ಪಾದನೆ ಹೆಚ್ಚಳ • Yield Increase" else "Yield Increase",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
