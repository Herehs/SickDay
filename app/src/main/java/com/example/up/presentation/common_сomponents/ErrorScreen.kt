package com.example.up.presentation.common_сomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDim


@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.network_off
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                modifier = Modifier.size(120.dp),
                tint = text,
                painter = painterResource(icon),
                contentDescription = null
            )
            Text(
                text = "Ошибка сети",
                fontSize = 14.sp,
                color = textDim
            )
        }
    }
}