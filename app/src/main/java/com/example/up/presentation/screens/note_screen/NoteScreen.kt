package com.example.up.presentation.screens.note_screen

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.up.R
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.screens.note_screen.components.CustomSlider
import com.example.up.presentation.screens.note_screen.components.NoteQualityComponent
import com.example.up.presentation.ui.theme.text
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun NoteScreen(
    modifier: Modifier,
    onBackButton: () -> Unit,
    noteViewModel: NoteViewModel = koinViewModel()
) {
    BackHandler {
        onBackButton()
    }

    val note by noteViewModel.note.collectAsStateWithLifecycle()


    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 30.dp)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            IconButton(
                onClick = {
                    noteViewModel.saveNote(
                        onComplete = onBackButton
                    )
                },
                interactionSource = null,
                modifier = Modifier.size(30.dp),
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
            IconButton(
                onClick = {
                    noteViewModel.deleteNote(
                        onComplete = onBackButton
                    )
                },
                interactionSource = null,
                modifier = Modifier.size(30.dp),
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = text,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color(0xff141414).copy(alpha = .5f)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.trash),
                    contentDescription = null
                )
            }
        }
        Section(
            modifier = Modifier.padding(horizontal = 20.dp),
            name = "Общее состояние"
        ) {
            CustomSlider(
                value = note.generalHealth,
                onValueChange = noteViewModel::onGeneralHealthChanged,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xffFF9C9D),
                    activeTrackColor = Color(0xffFF9C9D),
                    inactiveTrackColor = Color(0xff9C9C9C),
                ),
                steps = 10
            )
        }
        Section(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp),
            name = "Заметки"
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                value = note.note,
                onValueChange = noteViewModel::onNoteTextChanged,
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
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp),
            name = "Симптомы"
        ){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ){
                item{
                    NoteQualityComponent(
                        label = "Сонливость",
                        sliderValue = note.drowsiness,
                        onSliderPositionChanged = noteViewModel::onDrowsinessChanged
                    )
                }
                item {
                    NoteQualityComponent(
                        label = "Давление",
                        sliderValue = note.pressure,
                        onSliderPositionChanged = noteViewModel::onPressureChanged
                    )
                }
                item {
                    NoteQualityComponent(
                        label = "Слабость",
                        sliderValue = note.weakness,
                        onSliderPositionChanged = noteViewModel::onWeaknessChanged
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
        NoteScreen(
            modifier = Modifier.padding(it),
            onBackButton = TODO()
        )
    }
}
