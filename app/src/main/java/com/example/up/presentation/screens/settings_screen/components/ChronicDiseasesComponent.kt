package com.example.up.presentation.screens.settings_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.up.R
import com.example.up.domain.model.ChronicDiseasesState
import com.example.up.domain.model.Disease
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.screens.settings_screen.SettingsItem

@Composable
fun ChronicDiseasesComponent(
    modifier: Modifier = Modifier,
    chronicDiseasesState: ChronicDiseasesState
) {
    var state by remember { mutableStateOf(chronicDiseasesState) }
    Section(
        modifier = modifier
            .fillMaxWidth(),
        name = "Хронические заболевания"
    ) {
        SelectedList(
            state = state,
            onItemClick = { clickedItem ->
                state = state.copy(
                    diseaseList = state.diseaseList.map {
                        if (it.name == clickedItem.name)
                            it.copy(selected = !it.selected)
                        else it
                    }
                )
            }
        )

        AddList(
            state = state,
            onItemClick = { clickedItem ->
                state = state.copy(
                    diseaseList = state.diseaseList.map {
                        if (it.name == clickedItem.name)
                            it.copy(selected = !it.selected)
                        else it
                    }
                )
            }
        )
    }
}

@Composable
fun SelectedList(
    modifier: Modifier = Modifier,
    state: ChronicDiseasesState,
    onItemClick: (Disease) -> Unit
){
    val selectedItems = state.diseaseList.filter { it.selected }
    Column(
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
        state.diseaseList.forEachIndexed { index, item ->
            AnimatedVisibility(
                item.selected
            ) {
                Column {
                    if( selectedItems.firstOrNull() != item){
                        HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                    }
                    SettingsItem(
                        name = item.name
                    ) {
                        Icon(
                            painter = painterResource(
                                R.drawable.minus
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .height(14.dp)
                                .padding(end = 14.dp)
                                .clickable(
                                    interactionSource = null,
                                    onClick = {
                                        onItemClick(item)
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

@Composable
fun AddList(
    state: ChronicDiseasesState,
    onItemClick: (Disease) -> Unit
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
        SettingsItem(
            name = "Добавить"
        ) {
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
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                state.diseaseList.forEachIndexed { index, item ->
                    AnimatedVisibility(
                        !item.selected
                    ){
                        Column(
                            modifier = Modifier
                        ) {
                            HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                            SettingsItem(
                                name = item.name
                            ) {
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
                                                onItemClick(item)
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

}

@Preview
@Composable
fun ChronicDiseasesComponentTest() {
    val state = ChronicDiseasesState(
        diseaseList = listOf(
            Disease(name = "Гипертония", selected = true),
            Disease(name = "Заболевания суставов", selected = true),
            Disease(name = "Диарея"),
            Disease(name = "Гонорея"),
            Disease(name = "Сифилис")
        )
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ChronicDiseasesComponent(chronicDiseasesState = state)
    }
}