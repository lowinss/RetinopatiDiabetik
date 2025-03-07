package com.example.retinopati.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun InfoCard(
    title: String,
    contentList: List<String>,
    modifier: Modifier = Modifier,
    leadText: String? = null,
) {
    ElevatedCard(
        shape = RoundedCornerShape(0),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = title, modifier = Modifier.align(Alignment.CenterHorizontally))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFFF0004),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 8.dp)
            )

            if (!leadText.isNullOrBlank()) {
                Text(text = leadText)
            }

            contentList.forEachIndexed { index, item ->
                Text(text = "${index + 1}. $item", modifier = Modifier.padding(start = 8.dp))
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFBABEC1),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun InfoCardPreview() {
    val diabetikList = listOf("1. ......", "2. .......", "3. .........", "4. .........")
    AppTheme {
        Scaffold {
            InfoCard(
                title = "Panduan Penggunaan Aplikasi",
                contentList = diabetikList,
                modifier = Modifier.padding(it)
            )
        }
    }
}