package com.developer.raitha_varta

import android.app.Application
import com.developer.raitha_varta.data.AppDatabase
import com.developer.raitha_varta.data.TipRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class RaithaVartaApp : Application() {

    // 1. Initialize Firestore instance
    private val firestore by lazy { FirebaseFirestore.getInstance() }

    // 2. Room Database
    val database by lazy { AppDatabase.getDatabase(this) }

    // 3. Repository (Now passing all 3 required dependencies)
    val repository by lazy {
        TipRepository(
            tipDao = database.tipDao(),
            successStoryDao = database.successStoryDao(),
            firestore = firestore
        )
    }

    override fun onCreate() {
        super.onCreate()
        // Firebase initialization
        FirebaseApp.initializeApp(this)
    }
}