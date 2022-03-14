package com.paging3demo.ui.presentation.dogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paging3demo.R
import com.paging3demo.domain.model.Dog
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DogItem(index: Int, dog: Dog) {

    Row()
    {
        GlideImage(
            imageModel = dog.picture,
            placeHolder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(bottom=5.dp)
        )
    }
}