package com.developer.raitha_varta.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow // This replaces asFlow()
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

class HomeViewModel(private val repository: TipRepository) : ViewModel() {


    var selectedCategory = mutableStateOf("all")
        private set

    var isLoading = mutableStateOf(false)
        private set

    init {
        refreshTipsFromAdvisor()
    }
    val tips: StateFlow<List<TipEntity>> = repository.allTips
        .combine(snapshotFlow { selectedCategory.value }) { allTips, category ->
            android.util.Log.d("FilterCheck", "Current Category: $category, Total Tips: ${allTips.size}")

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

    fun updateCategory(categoryId: String) {
        println("DEBUG: Category changed to $categoryId")
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

class HomeViewModelFactory(private val repository: TipRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SuccessViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuccessViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
