package com.example.up.presentation.screens.note_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.common_сomponents.Background
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.screens.note_screen.components.CustomSlider
import com.example.up.presentation.screens.note_screen.components.NoteQualityComponent
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(modifier: Modifier) {

    val currDate = LocalDate.now()
    var note by rememberSaveable { mutableStateOf("") }

    var sliderValue by rememberSaveable { mutableFloatStateOf(0f) }

    Background()
    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 30.dp)
                .padding(horizontal = 70.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            IconButton(
                onClick = {  },
                interactionSource = null,
                modifier = Modifier.size(22.dp),
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = text,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color(0xff141414).copy(alpha = .5f)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.left_arrow),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = currDate.format(DateTimeFormatter.ofPattern("Заметка от dd LLLL")).replaceFirstChar { it.uppercase() },
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400
            )
            IconButton(
                onClick = {  },
                interactionSource = null,
                modifier = Modifier.size(22.dp),
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = text,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color(0xff141414).copy(alpha = .5f)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.right_arrow),
                    contentDescription = null
                )
            }
        }
        Section(
            modifier = Modifier.padding(horizontal = 20.dp),
            name = "Общее состояние"
        ) {
            CustomSlider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xffFF9C9D),
                    activeTrackColor = Color(0xffFF9C9D),
                    inactiveTrackColor = Color(0xff9C9C9C),
                ),
                steps = 10
            )
        }
        Section(
            modifier = Modifier.padding(horizontal = 20.dp).padding(top = 40.dp),
            name = "Заметки"
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                value = note,
                onValueChange = {note = it},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor= Color(0xff9F8A8F).copy(alpha = .3f),
                    unfocusedBorderColor = Color(0xff9F8A8F).copy(alpha = .3f),
                    cursorColor = Color(0xff9F8A8F).copy(alpha = .3f),
                    focusedTextColor = text,
                    unfocusedTextColor = text
                ),
                minLines = 4,
                maxLines = 4
            )
        }
        Section(
            modifier = Modifier.padding(horizontal = 20.dp).padding(top = 40.dp),
            name = "Симптомы"
        ){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ){
                item{
                    NoteQualityComponent(
                        label = "Сонливость"
                    )
                }
                item {
                    NoteQualityComponent(
                        label = "Давление"
                    )
                }
                item {
                    NoteQualityComponent(
                        label = "Слабость"
                    )
                }
                item {
                    NoteQualityComponent(
                        label = "Диарея"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun NoteScreenTest(){
    Scaffold {
        NoteScreen(modifier = Modifier.padding(it))
    }
}
