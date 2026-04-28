package com.example.up.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.mainFontFamily
import com.example.up.presentation.ui.theme.text

@Composable
fun DateCarousel(
    modifier: Modifier = Modifier
){
    val dateList = listOf(
        DateCardData(number = "1", name = "Понедельник"),
        DateCardData(number = "2", name = "Вторник"),
        DateCardData(number = "3", name = "Среда"),
        DateCardData(number = "4", name = "Четверг"),
        DateCardData(number = "5", name = "Понедельник")

    )
    val lazyRowState = rememberLazyListState()
    val centerItemIndex = remember {
        derivedStateOf {
            val layoutInfo = lazyRowState.layoutInfo

            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            layoutInfo.visibleItemsInfo.minByOrNull { item ->
                val itemCenter = item.offset + item.size / 2
                kotlin.math.abs(itemCenter - viewportCenter)
            }?.index
        }
    }
    LazyRow(
        state = lazyRowState,
        modifier = modifier
    ) {
        itemsIndexed(dateList){ index, card ->
            if (index == centerItemIndex.value) card.selected = true else card.selected = false
            DateCard(card)
        }
    }
}

data class DateCardData(
    val number: String,
    val name: String,
    var selected: Boolean = false
)

@Composable
fun DateCard(
    data: DateCardData,
){
    val numberSize = if(data.selected) 36.sp else 24.sp
    val width = if(data.selected) 126.dp else 80.dp
    val height = if(data.selected) 100.dp else 69.dp

    Column(
        modifier = Modifier
            .padding(5.dp)
            .width(width)
            .height(height)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xffFFFAEF))
            .border(
                width = 1.dp,
                color = Color(0xffFBB672),
                shape = RoundedCornerShape(8.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier,
            text = data.number,
            fontSize = numberSize,
            lineHeight = 22.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W500
        )
        if(data.selected){
            Text(
                modifier = Modifier,
                text = data.name,
                fontSize = 13.sp,
                lineHeight = 22.sp,
                fontFamily = mainFontFamily,
                color = text,
                fontWeight = FontWeight.W500
            )
        }
    }
}

@Preview
@Composable
fun CarouselPreview(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

    }
    DateCarousel()
}