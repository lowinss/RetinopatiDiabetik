package com.example.retinopati.core.presentation.screen.submit

import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil3.compose.rememberAsyncImagePainter
import com.example.retinopati.BuildConfig
import com.example.retinopati.R
import com.example.retinopati.core.presentation.components.CustomButton
import com.example.retinopati.core.presentation.components.RetinopatiToolbar
import com.example.retinopati.core.utils.createImageFile
import com.example.retinopati.ui.theme.AppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.util.Objects

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SubmitContent(onClickProceed: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".FileProvider", file
    )

    var localImageUri: Uri by remember { mutableStateOf(Uri.EMPTY) }

    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    // Track if the permission request has been processed after user interaction
    var hasRequestedPermission by rememberSaveable { mutableStateOf(false) }
    var permissionRequestCompleted by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(cameraPermissionState.status) {
        // Check if the permission state has changed after the request
        if (hasRequestedPermission) {
            permissionRequestCompleted = true
        }
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            localImageUri = uri
            Log.d("URI", "URI : $localImageUri")
        }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { galleryUri: Uri? ->
            uri?.let {
                localImageUri = it
                Log.d("URI", "Gallery URI : $it ")
            }
        }
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
                painter = rememberAsyncImagePainter(
                    if (localImageUri.path?.isNotEmpty() == true) localImageUri else R.drawable.logo
                ),
                contentDescription = "null",
                modifier = Modifier
                    .size(width = 240.dp, height = 0.dp)
                    .weight(1f)
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


                CustomButton(onClick = {
                    galleryLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }, text = "Galeri")
                CustomButton(onClick = {
                    if (cameraPermissionState.status.isGranted) {
                        cameraLauncher.launch(uri)
                    } else {
                        cameraPermissionState.launchPermissionRequest()
                        hasRequestedPermission = true
                    }
                }, text = "Kamera")
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