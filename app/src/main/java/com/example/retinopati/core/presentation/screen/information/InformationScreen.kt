package com.example.retinopati.core.presentation.screen.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retinopati.core.components.CustomButton
import com.example.retinopati.core.components.RetinopatiToolbar
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun InformationScreen(modifier: Modifier = Modifier) {

}

@Composable
fun InformationContent(onClickHome: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            RetinopatiToolbar(
                title = "INFORMASI",
                onBackClick = {},
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text("Ini halaman Informasi")
            CustomButton(onClick = onClickHome, text = "Kembali")
        }
    }
}

@Preview
@Composable
private fun InformationContentPreview() {
    AppTheme {
        InformationContent(onClickHome = {})
    }
}