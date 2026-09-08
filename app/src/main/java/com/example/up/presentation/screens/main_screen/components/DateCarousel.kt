package com.example.up.presentation.screens.main_screen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.math.pow

@Composable
fun DateCarousel(
    modifier: Modifier = Modifier,
    pickedDay: (DateCardData) -> Unit,
){
    val listSize = 61
    val dateList = getDateList(listSize)

    val lazyRowState = rememberLazyListState()

    LaunchedEffect(Unit) {
        lazyRowState.scrollToItem(listSize)
    }

    LazyRow(
        state = lazyRowState,
        modifier = modifier.padding(top = 20.dp),
        flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyRowState, snapPosition = SnapPosition.Center),
        contentPadding = PaddingValues(
            horizontal = (LocalConfiguration.current.screenWidthDp.dp / 2) - (60.dp / 2)
        )
    ) {
        itemsIndexed(dateList){ index, card ->
            val layoutInfo = lazyRowState.layoutInfo
            val itemInfo = layoutInfo.visibleItemsInfo.find { it.index == index }

            val offset = remember(itemInfo, layoutInfo) {
                if (itemInfo == null) {
                    0f
                } else {
                    val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
                    val itemCenter = itemInfo.offset + itemInfo.size / 2
                    val distance = abs(itemCenter - viewportCenter)
                    val maxDistance = layoutInfo.viewportSize.width / 2f

                    val fraction = (distance / maxDistance).coerceIn(0f, 1f)

                    val result = (fraction * 100).pow(2) * 0.01f - 0.01f
                    -result
                }
            }

            DateCard(
                modifier = Modifier.graphicsLayer{
                    translationY = offset
                },
                data = card.copy()
            )
        }
    }
}

@Composable
fun DateCard(
    data: DateCardData,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .padding(5.dp)
            .width(50.dp)
            .aspectRatio(3f/4f)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xFFD5DAFF))
            .border(
                width = 1.dp,
                color = Color(0xFF7289FB),
                shape = RoundedCornerShape(8.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier,
            text = data.date.format(DateTimeFormatter.ofPattern("dd")),
            fontSize = 24.sp,
            lineHeight = 22.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W500
        )


        Text(
            modifier = Modifier,
            text = data.date.format(DateTimeFormatter.ofPattern("E")).replaceFirstChar { it.uppercase() },
            fontSize = 12.sp,
            lineHeight = 18.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W500,
            maxLines = 1,
            textAlign = TextAlign.Center,
            letterSpacing = -1.sp
        )
    }
}

data class DateCardData(
    val date: LocalDate,
    val isSelected: Boolean = false,
)

fun getDateList(size: Int): List<DateCardData> {
    val today = LocalDate.now()
    val dateList = mutableListOf<DateCardData>()
    for(i in -size .. 0){
        dateList.add(
            DateCardData(
                date = today.plusDays(i.toLong())
            )
        )
    }
    return dateList
}

@Preview(
//    device = TABLET
)
@Composable
fun CarouselPreview(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        DateCarousel(pickedDay = {it})
    }
}