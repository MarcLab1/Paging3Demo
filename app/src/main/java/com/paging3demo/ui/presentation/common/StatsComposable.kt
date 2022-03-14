package com.paging3demo.ui.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun StatsComposable(count: Int) {
    Column() {
        Text(text = ("Loaded = ${count}"))
    }
}