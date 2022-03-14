package com.paging3demo.ui.presentation.books

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.paging3demo.ui.presentation.common.MyCircleProgressBar
import com.paging3demo.ui.presentation.common.MyErrorTextView
import com.paging3demo.ui.presentation.common.StatsComposable
import com.paging3demo.util.Constants

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BooksPage() {
    var booksViewModel: BooksViewModel = hiltViewModel()
    var lazyBooks = booksViewModel.books.collectAsLazyPagingItems()
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       StatsComposable(count = lazyBooks.itemCount)
        Box()
        {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    Row(modifier = Modifier.fillMaxWidth())
                    {
                        TextField(
                            value = booksViewModel.query.value,
                            onValueChange = { booksViewModel.updateQuery(it) },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = ""
                                )
                            },
                            placeholder = {
                                Text(text = "Search books")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    booksViewModel.searchBooks()
                                    keyboard?.hide()
                                })
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(onClick = {
                        booksViewModel.searchBooks()
                        keyboard?.hide()
                    }) {
                        Text(text = "search")
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                }

                itemsIndexed(lazyBooks)
                { index, book ->
                    if (book != null) {
                        BookItem(index = index, book = book, OnClick = {})
                    }
                }
            }

            //refresh is a new search, append is get the next page
            lazyBooks.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        MyCircleProgressBar()
                    }
                    loadState.append is LoadState.Loading -> {
                        MyCircleProgressBar()
                    }
                    loadState.refresh is LoadState.Error -> {
                        if(lazyBooks.itemCount == 0 ) {
                            val loadStateError = lazyBooks.loadState.refresh as LoadState.Error
                            MyErrorTextView(loadStateError.error.localizedMessage)
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        if(lazyBooks.itemCount == 0 ) {
                            val loadStateError = lazyBooks.loadState.append as LoadState.Error
                            MyErrorTextView(loadStateError.error.localizedMessage)
                        }
                    }
                }
            }
        }
    }
}