package com.paging3demo.ui.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyErrorTextView(error: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        Text(text = error, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    }
}