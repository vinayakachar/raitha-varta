package com.developer.raitha_varta.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

fun uploadExpertQuery(bitmap: Bitmap, context: Context) {
    val storage = FirebaseStorage.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    // 1. Create a unique filename
    val fileName = "expert_${System.currentTimeMillis()}.jpg"
    val storageRef = storage.reference.child("expert_queries/$fileName")

    // 2. Convert Bitmap to ByteArray
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
    val data = baos.toByteArray()

    // 3. Start Upload to Storage
    storageRef.putBytes(data).addOnSuccessListener {
        storageRef.downloadUrl.addOnSuccessListener { uri: Uri ->

            // 4. Save the Record to Firestore
            val queryData = hashMapOf(
                "imageUrl" to uri.toString(),
                "status" to "pending",
                "timestamp" to FieldValue.serverTimestamp(),
                "farmerName" to "Vinayaka R", // You can get this from user profile
                "id" to fileName
            )

            firestore.collection("expert_queries")
                .document(fileName)
                .set(queryData)
                .addOnSuccessListener {
                    Toast.makeText(context, "ಸಲ್ಲಿಸಲಾಗಿದೆ! (Submitted Successfully)", Toast.LENGTH_LONG).show()
                }
        }
    }.addOnFailureListener {
        Toast.makeText(context, "ಅಪ್‌ಲೋಡ್ ವಿಫಲವಾಗಿದೆ (Upload Failed)", Toast.LENGTH_SHORT).show()
    }
}
