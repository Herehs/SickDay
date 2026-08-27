package com.example.up.presentation.common_сomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDim

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp),
                strokeWidth = 6.dp,
                trackColor = text
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Загрузка...",
                fontSize = 14.sp,
                color = textDim
            )
        }
    }
}
