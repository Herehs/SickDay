package com.example.up.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text

@Composable
fun AdviceList(
    advices: List<Advice>
){
    LazyColumn() {
        items(advices){ advice ->
            Row(
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(advice.icon),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = advice.text,
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = text,
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

data class Advice(
    val icon: Int,
    val text: String
)


@Preview
@Composable
fun AdviceListTest(){
    val advice = Advice(icon = R.drawable.heart_rate, text = "hjhhg jkhbhjkghg khgbhgjhj kjjkkhghjftj fhkjhjgh mnftihg")
    val adviceList = mutableListOf<Advice>()

    repeat(20){
        adviceList.add(advice)
    }

    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).padding(horizontal = 10.dp)
        ){
            AdviceList(adviceList)
        }
    }
}