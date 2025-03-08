package com.example.retinopati.core.presentation.screen.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.rememberAsyncImagePainter
import com.example.retinopati.R
import com.example.retinopati.core.presentation.components.CustomButton
import com.example.retinopati.core.presentation.components.RetinopatiToolbar
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun ResultContent(
    uri: String,
    onClickBack: () -> Unit, modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            RetinopatiToolbar(
                title = "HALAMAN CEK",
                onClickBack = onClickBack,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Image(
                painter = rememberAsyncImagePainter(
                    if (uri.isNotBlank()) uri.toUri() else R.drawable.image
                ),
                contentDescription = "null",
                modifier = Modifier
                    .width(360.dp)
                    .height(320.dp)
            )
            CustomButton(onClick = {}, text = "Hasil")
            val placeholderText = LoremIpsum(85).values.toList().first().toString()
            Text(
                text = placeholderText,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ResultContentPreview() {
    AppTheme {
        ResultContent(uri = "", onClickBack = {})
    }
}