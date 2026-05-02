package com.example.up.presentation.main_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text

@Composable
fun DateCarousel(
    modifier: Modifier = Modifier,
    pickedDay: (DateCardData) -> Unit,
    startDay: Int = 0
//    dateList: List<DateCardData>
){
    val dateList = listOf(
        DateCardData(number = "1", name = "Понедельник"),
        DateCardData(number = "2", name = "Вторник"),
        DateCardData(number = "3", name = "Среда"),
        DateCardData(number = "4", name = "Четверг"),
        DateCardData(number = "5", name = "Пятница"),
        DateCardData(number = "6", name = "Суббота"),
        DateCardData(number = "7", name = "Воскресенье"),
        DateCardData(number = "8", name = "Понедельник"),
        DateCardData(number = "9", name = "Вторник"),
        DateCardData(number = "10", name = "Среда"),
        DateCardData(number = "11", name = "Четверг"),
        DateCardData(number = "12", name = "Пятница"),
        DateCardData(number = "13", name = "Суббота"),
        DateCardData(number = "14", name = "Воскресенье"),

    )

    val lazyRowState = rememberLazyListState()
    val centerItemIndex = remember {
        derivedStateOf {
            val layoutInfo = lazyRowState.layoutInfo
            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            println("viewportCenter ${viewportCenter}")
            layoutInfo.visibleItemsInfo.minByOrNull { item ->
                val itemCenter = item.offset + item.size / 2
                kotlin.math.abs(itemCenter - viewportCenter)
            }?.index
        }
    }
    LaunchedEffect(Unit) {
        lazyRowState.scrollToItem(startDay)
    }

    LazyRow(
        state = lazyRowState,
        modifier = modifier,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyRowState, snapPosition = SnapPosition.Center),
        contentPadding = PaddingValues(
            horizontal = (LocalConfiguration.current.screenWidthDp.dp / 2) - (126.dp / 2)
        )
    ) {
        itemsIndexed(dateList){ index, card ->
            var isSelected = false
            if(index == centerItemIndex.value){
                isSelected = true
                pickedDay(card)
            }
            DateCard(card.copy(selected = isSelected))
        }
    }
}

data class DateCardData(
    val number: String,
    val name: String,
    var selected: Boolean = false,
    var scale: Float = 1f
)

@Composable
fun DateCard(
    data: DateCardData,
){
    val numberSize = if(data.selected) 36.sp else 24.sp
    val width = animateDpAsState(if(data.selected) 126.dp else 80.dp)
    val height = animateDpAsState(if(data.selected) 100.dp else 67.dp)


    Column(
        modifier = Modifier
            .padding(5.dp)
            .width(width.value)
            .height(height.value)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xffFFFAEF))
            .border(
                width = 1.dp,
                color = Color(0xffFBB672),
                shape = RoundedCornerShape(8.dp)
            )
            .graphicsLayer(
                translationX = 1f
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

        AnimatedVisibility(
            visible = data.selected,
            enter = slideInVertically(initialOffsetY = { -20 }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = {-20}) + fadeOut()
        ) {
            Text(
                modifier = Modifier,
                text = data.name,
                fontSize = 13.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
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
        DateCarousel(pickedDay = {it})
    }
}