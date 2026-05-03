package com.example.up.presentation.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text

@Composable
fun Section(
    modifier: Modifier = Modifier,
    name: String = "",
    content: @Composable () -> Unit
){
    Column(modifier = modifier) {
        Text(
            modifier = Modifier,
            text = name,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W400,
            letterSpacing = -(0.8).sp
        )
        content()
    }

}

@Preview(showBackground = true)
@Composable
fun SectionTest(){
    Section(name = "Sekciya") {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Red)
        )
    }
}