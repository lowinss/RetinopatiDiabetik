package com.example.retinopati.core.presentation.screen.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.retinopati.core.utils.ListItem
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun InformationContent(onClickBack: () -> Unit, modifier: Modifier = Modifier) {
    val guideUsageSteps = listOf(
        ListItem(stringResource(R.string.guide_usage_step1), stringArrayResource(R.array.subguide_usage_step1).toList()),
        ListItem(stringResource(R.string.guide_usage_step2),stringArrayResource(R.array.subguide_usage_step2).toList()),
        ListItem(stringResource(R.string.guide_usage_step3), stringArrayResource(R.array.subguide_usage_step3).toList())
    )
    val guideCaptureSteps = listOf(
        ListItem(stringResource(R.string.guide_capture_step1)),
        ListItem(stringResource(R.string.guide_capture_step2)),
        ListItem(stringResource(R.string.guide_capture_step3))
    )
    val guidePredictionList = listOf(
        ListItem(stringResource(R.string.prediction_list1)),
        ListItem(stringResource(R.string.prediction_list2)),
        ListItem(stringResource(R.string.prediction_list3)),
        ListItem(stringResource(R.string.prediction_list4)),
        ListItem(stringResource(R.string.prediction_list5)),
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
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            InfoCard(title = stringResource(R.string.guide_capture_title), contentList = guideCaptureSteps)
            InfoCard(title = stringResource(R.string.guide_usage_title), contentList = guideUsageSteps)
            InfoCard(title = stringResource(R.string.prediction_list_title), leadText = stringResource(R.string.prediction_list_lead), contentList = guidePredictionList)
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