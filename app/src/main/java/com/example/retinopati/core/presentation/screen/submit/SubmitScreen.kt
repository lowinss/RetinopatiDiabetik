package com.example.retinopati.core.presentation.screen.submit

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.retinopati.R
import com.example.retinopati.core.data.network.ApiService
import com.example.retinopati.core.di.AppContainer
import com.example.retinopati.core.presentation.components.CustomButton
import com.example.retinopati.core.presentation.components.RetinopatiToolbar
import com.example.retinopati.core.utils.getImageUri
import com.example.retinopati.core.utils.reduceFileImageAsync
import com.example.retinopati.core.utils.uriToFile
import com.example.retinopati.ui.theme.AppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SubmitContent(
    onClickBack: () -> Unit,
    onClickProceed: (uri: String, prediction: String) -> Unit,
    modifier: Modifier = Modifier,
    apiService: ApiService = AppContainer.apiService,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val viewModel = remember { SubmitViewModel(apiService) }

    val isLoading by viewModel.isLoading

    var previewImageUri: Uri by rememberSaveable { mutableStateOf(Uri.EMPTY) }
    var cameraUri by remember { mutableStateOf(Uri.EMPTY) }

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
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                previewImageUri = cameraUri
            } else {
                cameraUri = Uri.EMPTY
                previewImageUri = Uri.EMPTY
            }
        }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { galleryUri: Uri? ->
            galleryUri?.let {
                previewImageUri = it
            }
        }
    )

    Scaffold(
        topBar = {
            RetinopatiToolbar(
                title = "CEK",
                onClickBack = onClickBack,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.weight(0.3f))
                AsyncImage(
                    model =
                        if (previewImageUri.path?.isNotEmpty() == true) previewImageUri else R.drawable.image,
                    contentDescription = "null",
                    modifier = Modifier
                        .width(360.dp)
                        .height(320.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                ) {
                    CustomButton(onClick = {
                        galleryLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }, text = "Galeri")
                    CustomButton(onClick = {
                        if (cameraPermissionState.status.isGranted) {
                            cameraUri = getImageUri(context)
                            cameraLauncher.launch(cameraUri)
                        } else {
                            cameraPermissionState.launchPermissionRequest()
                            hasRequestedPermission = true
                        }
                    }, text = "Kamera")
                }
                CustomButton(onClick = {
                    coroutineScope.launch {
                        if (previewImageUri != Uri.EMPTY) {
                            val file = withContext(Dispatchers.IO) {
                                uriToFile(context, previewImageUri).reduceFileImageAsync()
                            }

                            val predict = viewModel.predict(file)
                            onClickProceed(previewImageUri.toString(),predict.prediction)
                        }
                    }
                }, text = "PROSES")
                Spacer(modifier = Modifier.weight(0.3f))
            }
        }
    }
}

@Preview
@Composable
fun SubmitContentPreview() {
    AppTheme {
        SubmitContent(onClickBack = {}, onClickProceed = { _, _ ->
        })
    }
}


