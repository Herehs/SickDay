package com.example.up.presentation.screens.notes_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.domain.model.Note
import com.example.up.presentation.screens.notes_screen.util.toLocalDate
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDim
import java.time.format.DateTimeFormatter

@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier = Modifier,
    onNoteClick: (Long?) -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(25f / 5f),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, color = text),
        onClick = { onNoteClick(note.id) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 9.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = note.date.toLocalDate()
                    .format(DateTimeFormatter.ofPattern("Заметка от d.MM.yyy")),
                fontSize = 18.sp,
                color = text
            )
            Text(
                text = note.note,
                color = textDim,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Preview
@Composable
fun Test(){
    val note = Note(
        id = 1L,
        date = 1787702400,
        generalHealth = 0f,
        note = "sdsdsdasdsdasdasdsadsadssadasdssdddsdsdsdsddsdsddssdsdsdsddsdssdsdsdsdsdsdsdssddsdsdsdsddsadsadasdasdasdasd",
        drowsiness = 0f,
        pressure = 0f,
        weakness = 0f
    )
//    NoteCard(note)
}