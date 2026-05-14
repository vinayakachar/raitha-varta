package com.developer.raitha_varta.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.raitha_varta.data.SuccessStoryEntity
import com.developer.raitha_varta.data.TipRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SuccessViewModel(private val repository: TipRepository) : ViewModel() {

    var isLoading = mutableStateOf(false)
        private set

    val stories: StateFlow<List<SuccessStoryEntity>> = repository.allSuccessStories
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        refreshStories()
    }

    fun refreshStories() {
        viewModelScope.launch {
            isLoading.value = true
            repository.refreshSuccessStories()
            isLoading.value = false
        }
    }
}
