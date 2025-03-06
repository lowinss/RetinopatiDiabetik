package com.example.retinopati.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retinopati.R
import com.example.retinopati.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetinopatiToolbar(title: String, onBackClick: () -> Unit,modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            FilledTonalIconButton(onClick = onBackClick,
                shape = RoundedCornerShape(40),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White),
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "Kembali",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    )
}

@Preview
@Composable
private fun ToolbarPreview() {
    AppTheme {
        RetinopatiToolbar(
            title = "Percobaan",
            onBackClick = {}
        )
    }
}