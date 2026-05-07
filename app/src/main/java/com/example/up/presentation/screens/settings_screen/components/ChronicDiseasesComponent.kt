package com.example.up.presentation.screens.settings_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text

@Composable
fun ChronicDiseasesComponent(
    modifier: Modifier = Modifier
) {
    val diseaseList = rememberSaveable { mutableStateListOf("Гипертония", "Заболевания суставов", "Гипертония2", "Гипертония3") }
    val selectedDisease = rememberSaveable { mutableStateListOf("Гипертония4", "Гипертония5") }
    Section(
        modifier = modifier
            .fillMaxWidth(),
        name = "Хронические заболевания"
    ) {
        //selected disease
        SelectedList(
            modifier = Modifier.padding(top = 10.dp),
            list = selectedDisease,
            itemToRemove = { item ->
                selectedDisease.remove(item)
                diseaseList.add(item)
            }
        )
        //add new
        AddList(
            list = diseaseList,
            itemToRemove = { item ->
                diseaseList.remove(item)
                selectedDisease.add(item)
            }
        )

    }
}

@Composable
fun SelectedList(
    modifier: Modifier = Modifier,
    list: MutableList<String>,
    itemToRemove: (String) -> Unit
){
    LazyColumn(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(11.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh,
                    visibilityThreshold = IntSize.Zero,
                )
            )
    ) {
        itemsIndexed(
            list,
            key = { _, item -> item}
        ){ index, item ->
            val itemOpened = item in list
            Column {
                if(index > 0){
                    HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                }
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .animateItem(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = item,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontFamily = bodyFontFamily,
                        color = text,
                        fontWeight = FontWeight.W400,
                        letterSpacing = -(0.8).sp
                    )
                    Icon(
                        painter = painterResource(
                            if(itemOpened) R.drawable.minus else R.drawable.plus
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .height(14.dp)
                            .padding(end = 14.dp)
                            .clickable(
                                interactionSource = null,
                                onClick = {
                                    itemToRemove(item)
                                }
                            ),
                        tint = Color(0xff6C706D)
                    )
                }

            }
        }
    }
}

@Composable
fun AddList(
    list: MutableList<String>,
    itemToRemove: (String) -> Unit
){
    var opened by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(11.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh,
                    visibilityThreshold = IntSize.Zero,
                )
            )
    ){
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = "Добавить",
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400,
                letterSpacing = -(0.8).sp
            )
            Icon(
                painter = painterResource(
                    if(opened) R.drawable.minus else R.drawable.plus
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(14.dp)
                    .padding(end = 14.dp)
                    .clickable(
                        interactionSource = null,
                        onClick = { opened = !opened }
                    ),
                tint = Color(0xff6C706D)
            )
        }
        AnimatedVisibility(opened) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    list,
                    key = { item -> item },
                ){ item ->
                    Column(
                        modifier = Modifier
                            .animateItem()
                    ) {
                        HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                        Row(
                            modifier = Modifier
                                .height(48.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = item,
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                fontFamily = bodyFontFamily,
                                color = text,
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.8).sp
                            )
                            Icon(
                                painter = painterResource(
                                    R.drawable.plus
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(14.dp)
                                    .padding(end = 14.dp)
                                    .clickable(
                                        interactionSource = null,
                                        onClick = {
                                            itemToRemove(item)
                                        }
                                    ),
                                tint = Color(0xff6C706D)
                            )
                        }

                    }

                }
            }
        }
    }

}

data class Disease(
    val name: String
)
@Preview
@Composable
fun ChronicDiseasesComponentTest() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ChronicDiseasesComponent()
    }
}