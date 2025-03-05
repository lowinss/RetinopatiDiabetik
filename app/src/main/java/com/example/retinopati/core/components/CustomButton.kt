package com.example.retinopati.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retinopati.ui.theme.AppTheme

@Composable
fun CustomButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        modifier = modifier
            .padding(vertical = 12.dp)
            .shadow(
                elevation = 2.dp,
                ambientColor = Color.Yellow,
                spotColor = Color.Red,
                shape = RoundedCornerShape(40)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomButtonPreview() {
    AppTheme {
        CustomButton(onClick = {}, text = "Coba")
    }
}