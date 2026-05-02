package com.example.up.presentation.main_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text


@Composable
fun Tile(
    modifier: Modifier = Modifier,
    name: String,
    tileContent: @Composable (() -> Unit)
){
    Column(
        modifier = modifier
            .width(174.dp)
            .height(84.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = Color(0xffCCA7A7),
                shape = RoundedCornerShape(6.dp)
            ),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.padding(top = 10.dp, start = 16.dp)
        ) {
            Text(
                modifier = Modifier,
                text = name,
                fontSize = 13.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400
            )
            tileContent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TileTest(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Tile(
            name = "hui",
            tileContent = {

                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                                fontSize = 32.sp,
                                fontFamily = bodyFontFamily,
                                color = text,
                                fontWeight = FontWeight.W400
                            )
                        ){
                            append("777 ")
                        }
                        withStyle(style = SpanStyle(
                            fontSize = 16.sp,
                            fontFamily = bodyFontFamily,
                            color = text,
                            fontWeight = FontWeight.W400
                        )
                        ){
                            append("мм рт ст")
                        }
                    },
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = text,
                    fontWeight = FontWeight.W400
                )
            }
        )
    }
}