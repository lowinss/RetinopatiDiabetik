package com.example.retinopati.core.presentation.screen.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retinopati.R
import com.example.retinopati.core.presentation.components.InfoCard
import com.example.retinopati.core.presentation.components.RetinopatiToolbar
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun InformationScreen(modifier: Modifier = Modifier) {

}

@Composable
fun InformationContent(onClickBack: () -> Unit, modifier: Modifier = Modifier) {
    val citraList = listOf("Pertama", "Kedua", "Ketiga")
    val guideList = listOf("Pertama", "Kedua", "Ketiga")
    val diabetikList = listOf(
        "Non Retinopati Diabetik",
        "R. D. Ringan",
        "R. D. Sedang",
        "R. D. Berat",
        "R. D. Proliferatif"
    )

    Scaffold(
        topBar = {
            RetinopatiToolbar(
                title = stringResource(R.string.information_screen_title),
                onClickBack = onClickBack,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(40.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            InfoCard(title = stringResource(R.string.guide_capture_title), contentList = stringArrayResource(R.array.guide_capture_steps).toList())
            InfoCard(title = stringResource(R.string.guide_usage_title), contentList = stringArrayResource(R.array.guide_usage_steps).toList())
            InfoCard(title = stringResource(R.string.prediction_list_title), contentList = stringArrayResource(R.array.prediction_list).toList())
        }
    }
}

@Preview
@Composable
private fun InformationContentPreview() {
    AppTheme {
        InformationContent(onClickBack = {})
    }
}