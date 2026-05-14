package com.developer.raitha_varta.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.Flow

class TipRepository(
    private val tipDao: TipDao,
    private val successStoryDao: SuccessStoryDao,
    private val firestore: FirebaseFirestore
) {

    // --- Daily Tips Logic ---
    val allTips: Flow<List<TipEntity>> = tipDao.getAllTips()

    suspend fun refreshTips() {
        try {
            val snapshot = firestore.collection("tips").get().await()
            val remoteTips = snapshot.toObjects(TipEntity::class.java)

            if (remoteTips.isNotEmpty()) {
                tipDao.clearAll()
                tipDao.insertTips(remoteTips)
            }
        } catch (e: Exception) {
            Log.e("Firebase", "Tips fetch failed", e)
        }
    }

    // --- Success Stories Logic ---
    val allSuccessStories: Flow<List<SuccessStoryEntity>> = successStoryDao.getAllStories()

    suspend fun refreshSuccessStories() {
        try {
            val snapshot = firestore.collection("success_stories").get().await()
            val remoteStories = snapshot.toObjects(SuccessStoryEntity::class.java)

            if (remoteStories.isNotEmpty()) {
                successStoryDao.clearAll()
                successStoryDao.insertStories(remoteStories)
            }
        } catch (e: Exception) {
            Log.e("Firebase", "Success stories fetch failed", e)
        }
    }
}
