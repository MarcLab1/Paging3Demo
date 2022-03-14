package com.paging3demo.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.paging3demo.domain.model.Dog
import com.paging3demo.ui.presentation.common.MyCircleProgressBar
import com.paging3demo.ui.presentation.common.StatsComposable
import com.paging3demo.ui.presentation.dogs.DogItem
import com.paging3demo.ui.presentation.dogs.DogViewModel

@Composable
fun DogPage() {
    var dogViewModel: DogViewModel = hiltViewModel()
    val lazyDogs: LazyPagingItems<Dog> = dogViewModel.dogs.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StatsComposable(count = lazyDogs.itemCount)

        Box(modifier = Modifier.fillMaxSize())
        {
            LazyColumn()
            {
                itemsIndexed(lazyDogs)
                {   index, dog ->
                    if (dog != null) {
                        DogItem(index, dog)
                    }
                }
            }

            lazyDogs.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        MyCircleProgressBar()
                    }
                    loadState.append is LoadState.Loading -> {
                        MyCircleProgressBar()
                    }
                }
            }
        }
    }
}
