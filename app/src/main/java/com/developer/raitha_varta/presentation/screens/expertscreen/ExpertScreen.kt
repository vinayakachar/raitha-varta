package com.developer.raitha_varta.presentation.screens.expertscreen

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.raitha_varta.ui.theme.ForestGreen
import android.util.Base64
import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream

@Composable
fun ExpertScreen() {
    val configuration = LocalConfiguration.current
    val isKannada = configuration.locales[0].language == "kn"
    var isUploading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission allowed! Now we can launch the camera
            // (Note: To launch camera here, you'd need to trigger the cameraLauncher)
        } else {
            Toast.makeText(context, "Camera Permission is required to ask experts", Toast.LENGTH_SHORT).show()
        }
    }

    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            uploadQueryToFirestore(bitmap, context)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().height(500.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Top Progress Bar (Visual only as per screenshot)
                Box(
                    modifier = Modifier.fillMaxWidth(0.6f).height(4.dp)
                        .clip(CircleShape).background(ForestGreen)
                )

                Spacer(Modifier.height(40.dp))

                // Camera Icon
                Icon(
                    imageVector = Icons.Outlined.PhotoCamera,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = ForestGreen
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    text = if (isKannada) "ತಜ್ಞರನ್ನು ಕೇಳಿ" else "Ask the Expert",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Ask the Expert • Upload a photo of your crop issue",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(Modifier.height(40.dp))

                // Take Photo Button
                Button(
                    onClick = { val permissionCheckResult = androidx.core.content.ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.CAMERA
                    )

                        if (permissionCheckResult == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch(null)
                        } else {
                            // This triggers the system popup
                            permissionLauncher.launch(android.Manifest.permission.CAMERA)
                        }
                              },
                    enabled = !isUploading,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ForestGreen),
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("📷 ", fontSize = 18.sp)
                            Text(if (isKannada) "ಫೋಟೋ ತೆಗೆಯಿರಿ • Take Photo" else "Take Photo")
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Our experts will analyze your photo and provide solutions within 24 hours",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

fun bitmapToBase64(bitmap: Bitmap): String {
    val outputStream = ByteArrayOutputStream()
    // We compress to 40% to keep the string size small for Firestore
    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun uploadQueryToFirestore(capturedBitmap: Bitmap, context: Context) {
    val firestore = FirebaseFirestore.getInstance()
    val imageString = bitmapToBase64(capturedBitmap)

    val queryData = hashMapOf(
        "imageData" to imageString, // The photo is now text!
        "status" to "Pending",
        "farmerName" to "Vinayaka R",
        "timestamp" to FieldValue.serverTimestamp()
    )

    firestore.collection("expert_queries")
        .add(queryData)
        .addOnSuccessListener {
            Toast.makeText(context, "ಸಲ್ಲಿಸಲಾಗಿದೆ! (Submitted)", Toast.LENGTH_LONG).show()
        }
        .addOnFailureListener { e ->
            Log.e("ExpertUpload", "Error: ${e.message}")
        }
}