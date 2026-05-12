package com.developer.raitha_varta.presentation.screens.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.developer.raitha_varta.R

@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DailyTipPager() {
    val sampleTitle="Pest Control"
    val sampleInstruction="Add 20 ml of chlorpyripasis to 10 litre of water and spray on evening"
    val sampleImageUrl="https://images.unsplash.com/photo-1560493676-04071c5f467b"
    Card(
        modifier = Modifier.fillMaxWidth().height(500.dp).padding(16.dp),
        shape = RoundedCornerShape(28.dp),
        colors= CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(280.dp)) {
                GlideImage(
                    model =sampleImageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    transition = CrossFade
                ){
                    it.placeholder(R.color.placeholder_grey)
                        .error(R.color.error_red)
                }
                // Optional: Status Badge (Urgent)
                Surface(
                    color = Color.Red,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(12.dp).align(Alignment.TopEnd)
                ) {
                    Text(
                        "ತುರ್ತು",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = sampleTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20) // Forest Green
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Action Box (Emerald Green)
                Surface(
                    color = Color(0xFF10B981),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = sampleInstruction,
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "← Swipe for more instructions",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
