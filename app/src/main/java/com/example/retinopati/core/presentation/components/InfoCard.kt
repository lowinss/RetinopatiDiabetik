package com.example.retinopati.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retinopati.core.utils.ListItem
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun InfoCard(
    title: String,
    contentList: List<ListItem>,
    modifier: Modifier = Modifier,
    leadText: String? = null,
) {
    var number = 1
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
                Text(text = leadText, textAlign = TextAlign.Justify)
            }

            contentList.forEach { item ->
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp)) {
                    Text(text = "${number++}.", modifier = Modifier.width(24.dp))
                    Text(text = item.text, textAlign = TextAlign.Justify, modifier = Modifier.weight(1f))
                }
                item.subItems?.forEach { sub ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp)
                    ) {
                        Text(text = "•", modifier = Modifier.width(16.dp))
                        Text(text = sub, textAlign = TextAlign.Justify, modifier = Modifier.weight(1f))
                    }
                }
            }
        }

//            contentList.forEachIndexed { index, item ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 4.dp)
//                ) {
//                    Text(text = "${number++}.", modifier = Modifier.width(24.dp))
//                    Text(text = item, textAlign = TextAlign.Justify, modifier = Modifier.weight(1f))
//                }
//
//                subContentList?.get(index)?.forEach { sub ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 16.dp, bottom = 4.dp)
//                    ) {
//                        Text("•", modifier = Modifier.width(32.dp))
//                        Text(sub, modifier = Modifier.weight(1f))
//                    }
//                }
//            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFBABEC1),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }


@Preview
@Composable
private fun InfoCardPreview() {
    val subDiabetikList = listOf(
        ListItem("Item 1 with no children"),
        ListItem("Item 2 with bullets", listOf("Sub-item A", "Sub-item B")),
        ListItem("Item 3 no children")
    )
    AppTheme {
        Scaffold {
            InfoCard(
                title = "Panduan Penggunaan Aplikasi",
                contentList = subDiabetikList,
                modifier = Modifier.padding(it)
            )
        }
    }
}