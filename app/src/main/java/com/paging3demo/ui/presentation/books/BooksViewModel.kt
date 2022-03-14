package com.paging3demo.ui.presentation.books

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.paging3demo.domain.model.Book
import com.paging3demo.domain.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel
@Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private var _query = mutableStateOf("")
    var query = _query

    private val _books = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val books = _books

    fun searchBooks() {
        viewModelScope.launch {
            repository.getBooksFlow(query = query.value).cachedIn(viewModelScope).collect {
                _books.value = it
            }
        }
    }

    fun updateQuery(newQuery : String)
    {
        _query.value = newQuery
    }
}
