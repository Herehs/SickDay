package com.example.up.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.main_screen.components.Background
import com.example.up.presentation.main_screen.components.DateCarousel
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MainScreen(){
    val currentDate = LocalDate.now()
    val formatted = currentDate.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() }

    Scaffold() { innerPadding ->
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = formatted,
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400
            )
            DateCarousel(modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Preview
@Composable
fun TestMainScreen(){
    MainScreen()
}