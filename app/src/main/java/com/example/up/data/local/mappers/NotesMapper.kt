package com.example.up.data.local.mappers

import com.example.up.data.local.database.entity.NoteEntity
import com.example.up.domain.model.Note
import java.util.Date

fun NoteEntity.toDomain() = Note(
    id = id,
    date = date,
    generalHealth = generalHealth,
    note = note,
    drowsiness = drowsiness,
    pressure = pressure,
    weakness = weakness,
)

fun Note.toEntity() = NoteEntity(
    id = id,
    date = date,
    generalHealth = generalHealth,
    note = note,
    drowsiness = drowsiness,
    pressure = pressure,
    weakness = weakness,
)
