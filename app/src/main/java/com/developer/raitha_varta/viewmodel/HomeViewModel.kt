package com.developer.raitha_varta.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.developer.raitha_varta.data.TipEntity
import com.developer.raitha_varta.data.TipRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TipRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    // 1. Language State: Initialized from SharedPreferences (Login Choice)
    // Defaults to "kn" if no value was stored during login
    private val _currentLanguage = mutableStateOf(
        sharedPreferences.getString("selected_language", "kn") ?: "kn"
    )
    val currentLanguage: State<String> = _currentLanguage

    var selectedCategory = mutableStateOf("all")
        private set

    var isLoading = mutableStateOf(false)
        private set

    init {
        refreshTipsFromAdvisor()
    }

    val tips: StateFlow<List<TipEntity>> = repository.allTips
        .combine(snapshotFlow { selectedCategory.value }) { allTips, category ->
            if (category.trim().lowercase() == "all") {
                allTips
            } else {
                allTips.filter { it.category.trim().equalsIgnoreCase(category.trim()) }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun String.equalsIgnoreCase(other: String) = this.lowercase() == other.lowercase()

    // 2. Persistent Language Toggle
    fun toggleLanguage(context: android.content.Context, newLocale: String) {
        _currentLanguage.value = newLocale

        // 1. Persist the choice
        sharedPreferences.edit().putString("selected_language", newLocale).apply()

        // 2. Trigger the actual Locale change
        val locale = java.util.Locale(newLocale)
        java.util.Locale.setDefault(locale)

        val resources = context.resources
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // 3. Force the Activity to refresh its strings
        (context as? android.app.Activity)?.recreate()
    }

    private fun updateAppLocale(context: Context, languageCode: String) {
        val locale = java.util.Locale(languageCode)
        java.util.Locale.setDefault(locale)

        val resources = context.resources
        val config = resources.configuration
        config.setLocale(locale)

        // This updates the context resources so stringResource() picks up the change
        resources.updateConfiguration(config, resources.displayMetrics)

        // For immediate changes in Compose, sometimes a small activity 'recreate' is needed
        // if the strings don't update instantly.
        (context as? android.app.Activity)?.recreate()
    }

    fun updateCategory(categoryId: String) {
        selectedCategory.value = categoryId
    }

    fun refreshTipsFromAdvisor() {
        viewModelScope.launch {
            isLoading.value = true
            repository.refreshTips()
            isLoading.value = false
            selectedCategory.value = selectedCategory.value
        }
    }
}

class HomeViewModelFactory(
    private val repository: TipRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                HomeViewModel(repository, sharedPreferences) as T
            }
            modelClass.isAssignableFrom(SuccessViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                SuccessViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
