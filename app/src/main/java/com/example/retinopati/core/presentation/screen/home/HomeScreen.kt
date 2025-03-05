package com.example.retinopati.core.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.retinopati.R
import com.example.retinopati.core.components.CustomButton
import com.example.retinopati.core.components.RetinopatiToolbar
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun HomeContent(onClickCheck: () -> Unit, onClickInfo: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            RetinopatiToolbar(
                title = "HALAMAN UTAMA",
                onBackClick = {},
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.weight(1f)
            )
            CustomButton(onClick = onClickCheck, text = "CEK", modifier = Modifier.weight(0.15f))
            CustomButton(
                onClick = onClickInfo,
                text = "INFORMASI",
                modifier = Modifier.weight(0.15f)
            )
            Spacer(modifier = Modifier.weight(0.3f))
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun HomePreview() {
    AppTheme {
        HomeContent(
            onClickCheck = {},
            onClickInfo = { }
        )
    }
}

