package com.paging3demo.ui.presentation.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.paging3demo.domain.model.Book
import com.paging3demo.ui.presentation.common.MyRatingBar
import com.paging3demo.R

@Composable
fun BookItem(index: Int, book: Book, OnClick: () -> Unit ) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable {
                OnClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp), verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(index.toString())
            book.volumeInfo?.let {
                Column(
                    modifier = Modifier.fillMaxWidth(.25f).padding(end = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    GlideImage(
                        imageModel = it.imageLinks?.let { it1 ->
                            it1.smallThumbnail?.let { it2 ->
                                forceHttps(
                                    it2
                                )
                            }
                        },
                        placeHolder = painterResource(R.drawable.placeholder),
                        error = painterResource(R.drawable.placeholder),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .width(75.dp)
                            .height(75.dp)
                    )
                }
                Column() {

                    it.title?.let { it1 ->
                        Text(
                            it1,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.h2
                        )
                    }
                    it.subtitle?.let { it1 ->
                        Text(
                            it1,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.h3
                        )
                    }
                    it.authors?.let {
                        Row()
                        {
                            var authorsString = "by "
                            for (author in it) {
                                authorsString += author + ", "
                            }
                            Text(authorsString.dropLast(2), style = MaterialTheme.typography.h4)
                        }
                    }

                    it.averageRating?.let { it2->
                        it.ratingsCount?.let { it1 -> MyRatingBar(rating = it2.toInt(), ratingsCount = it1) }
                    }
                }
            }
        }
    }
}


fun forceHttps(input: String): String {
    if (input == null || input.length == 0) return ""

    return "https" + input.subSequence(input.indexOf(':'), input.length)
}