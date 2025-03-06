package com.example.retinopati

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.retinopati.core.presentation.components.CustomButton
import com.example.retinopati.core.presentation.navigation.NavigationRoot
import com.example.retinopati.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationRoot(
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Composable
fun InitialScreen(onClickStart: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .matchParentSize()
                .align(alignment = Alignment.Center)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                text = "APLIKASI DETEKSI DINI RETINOPATI DIABETIK",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
                Card(
                    shape = RoundedCornerShape(20),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .matchParentSize()
                        .padding(horizontal = 24.dp)
                        .alpha(0.55f)
                ) { }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                ) {
                    Spacer(modifier = Modifier.padding(6.dp))
                    HorizontalDivider(
                        thickness = 8.dp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 150.dp).clip(shape = RoundedCornerShape(100))
                    )
                    Text(
                        text = "RUMAH SAKIT UMUM DAERAH DOLOK SANGGUL",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 28.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 60.dp, vertical = 24.dp)
                    )
                    CustomButton(onClick = onClickStart, text = "MULAI")
                    Spacer(modifier = Modifier.padding(24.dp))
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AppTheme {
        InitialScreen(onClickStart = {})
    }
}

