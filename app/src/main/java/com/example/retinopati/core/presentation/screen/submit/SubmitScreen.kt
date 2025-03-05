package com.example.retinopati.core.presentation.screen.submit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retinopati.R
import com.example.retinopati.core.components.CustomButton
import com.example.retinopati.core.components.RetinopatiToolbar
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun SubmitContent(onClickProceed: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var localImageUri: Uri? by remember { mutableStateOf(null) }

    var hasCameraPermission by remember { mutableStateOf(false) }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? -> localImageUri = uri }
    )

    Scaffold(
        topBar = {
            RetinopatiToolbar(
                title = "CEK",
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
                painter = painterResource(R.drawable.logo),
                contentDescription = "Gambar",
                modifier = Modifier.weight(1f)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(40.dp),
            ) {
//                OutlinedButton(onClick = {
//                    galleryLauncher.launch(
//                        PickVisualMediaRequest(
//                            ActivityResultContracts.PickVisualMedia.ImageOnly
//                        )
//                    )
//                }) { Text(text = "Galeri") }
//                OutlinedButton(onClick = {
//                    cameraLauncher.launch(android.Manifest.permission.CAMERA)
//                }) { Text(text = "Kamera") }
                CustomButton(onClick = {}, text = "Galeri")
                CustomButton(onClick = {}, text = "Kamera")
            }
            CustomButton(onClick = onClickProceed, text = "PROSES")
//            OutlinedButton(onClick = onClickProceed) { Text(text = "PROSES") }
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}


@Preview
@Composable
fun SubmitContentPreview() {
    AppTheme {
        SubmitContent(onClickProceed = {})
    }
}