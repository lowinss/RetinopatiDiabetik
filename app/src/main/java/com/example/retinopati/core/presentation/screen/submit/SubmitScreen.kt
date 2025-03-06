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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.retinopati.R
import com.example.retinopati.core.presentation.components.CustomButton
import com.example.retinopati.core.presentation.components.RetinopatiToolbar
import com.example.retinopati.core.utils.getImageUri
import com.example.retinopati.ui.theme.AppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SubmitContent(onClickProceed: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
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
            localImageUri = getImageUri(context)
            Log.d("URI", "URI : $localImageUri")
        }
//
//    val galleryLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = { uri: Uri? -> localImageUri = uri }
//    )

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
            AsyncImage(
                model = localImageUri,
                contentDescription = "null",
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
                CustomButton(onClick = {
                    if (cameraPermissionState.status.isGranted) {
                        localImageUri = getImageUri(context)
                        cameraLauncher.launch(localImageUri)
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