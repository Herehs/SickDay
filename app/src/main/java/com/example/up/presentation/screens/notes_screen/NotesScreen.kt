package com.example.up.presentation.screens.notes_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room3.util.TableInfo
import com.example.up.R
import com.example.up.common.Resource
import com.example.up.domain.model.Note
import com.example.up.presentation.screens.notes_screen.components.NoteCard
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDim
import org.koin.androidx.compose.koinViewModel


@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    onAddClick: (Long?) -> Unit
){
    val notesViewModel: NotesViewModel = koinViewModel()
    val notesState by notesViewModel.notesState.collectAsState()
    when (notesState){
        is Resource.Success -> {
            NotesScreenSuccess(
                modifier = modifier,
                list = notesState,
                onAddClick = onAddClick
            )
        }
        is Resource.Loading -> {
            NotesScreenLoading(
                modifier = modifier
            )
        }
        is Resource.Error -> {
            NotesScreenLoading(
                modifier = modifier
            )
        }
    }

}

@Composable
fun NotesScreenSuccess(
    modifier: Modifier = Modifier,
    list: Resource<List<Note>>,
    onAddClick: (Long?) -> Unit
){
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(Modifier.height(height = 10.dp))
            }
            items(list.data ?: listOf()){ note ->
                NoteCard(
                    note = note,
                    onNoteClick = onAddClick
                )
                Spacer(Modifier.height(height = 5.dp))
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = { onAddClick(null) },
            containerColor = Color(0xffFFFAEF),
            contentColor = text,
            elevation = FloatingActionButtonDefaults.elevation(1.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.plus),
                contentDescription = null
            )
        }
    }
}

@Composable
fun NotesScreenLoading(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier.size(60.dp),
                strokeWidth = 6.dp,
                trackColor = text
            )
            Text(
                text = "Загрузка...",
                fontSize = 14.sp,
                color = textDim
            )
        }
    }
}

@Preview
@Composable
fun Test(){
    NotesScreen(
        modifier = TODO(),
        onAddClick = TODO()
    )
}