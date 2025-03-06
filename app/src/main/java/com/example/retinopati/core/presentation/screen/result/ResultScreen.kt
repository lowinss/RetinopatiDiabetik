package com.example.retinopati.core.presentation.screen.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retinopati.core.presentation.components.CustomButton
import com.example.retinopati.core.presentation.components.RetinopatiToolbar
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun ResultContent(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            RetinopatiToolbar(
                title = "HALAMAN CEK",
                onBackClick = {},
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Card {
                Text("Placeholder gambar")
            }
            CustomButton(onClick = {}, text = "Hasil")
            Text("Keterangan")
        }
    }
}

@Preview
@Composable
private fun ResultContentPreview() {
    AppTheme {
        ResultContent()
    }
}