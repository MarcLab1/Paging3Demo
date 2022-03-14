package com.paging3demo.ui.presentation.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.paging3demo.domain.model.Dog
import com.paging3demo.domain.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel()
{
    private val _dogs = MutableStateFlow<PagingData<Dog>>(PagingData.empty())
    val dogs = _dogs

    init {
        viewModelScope.launch {
            dogRepository.getDogFlow().cachedIn(viewModelScope).collect {
                _dogs.value = it
            }
        }
    }
}