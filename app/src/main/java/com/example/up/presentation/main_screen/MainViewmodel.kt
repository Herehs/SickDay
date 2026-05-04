package com.example.up.presentation.main_screen

import androidx.lifecycle.ViewModel
import com.example.up.R
import com.example.up.presentation.main_screen.components.Advice
import com.example.up.presentation.main_screen.components.PillsScheduleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.LocalTime

class MainViewmodel() : ViewModel() {

    private val _adviseList = MutableStateFlow(emptyList<Advice>())
    val adviseList = _adviseList.asStateFlow()

    private val _pillsList = MutableStateFlow(emptyList<PillsScheduleData>())
    val pillsList = _pillsList.asStateFlow()

    private val _atmPressure = MutableStateFlow(0)
    val atmPressure = _atmPressure.asStateFlow()

    private val _KRIndex = MutableStateFlow(0)
    val KRIndex = _KRIndex.asStateFlow()

    private val _temperature = MutableStateFlow(0)
    val temperature = _temperature.asStateFlow()

    private val _humidity = MutableStateFlow(0)
    val humidity = _humidity.asStateFlow()


    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    init {
        _adviseList.value = listOf(
            Advice(
                icon = R.drawable.heart_rate,
                text = "Снизьте физические нагрузки, избегайте резкого подъёма"
            ),
            Advice(
                icon = R.drawable.clock,
                text = "Пейте больше воды \n1.5–2 л в течение дня"
            ),
            Advice(
                icon = R.drawable.drop,
                text = "Ложитесь спать пораньше, ночью буря усилится"
            )
        )

        _pillsList.value = listOf(
            PillsScheduleData(
                name = "Фенозепам",
                date = LocalDate.now(),
                time = LocalTime.of(12, 0)
            ),
            PillsScheduleData(
                name = "Лирика",
                date = LocalDate.now(),
                time = LocalTime.of(16, 0),
                taken = true
            ),
            PillsScheduleData(
                name = "Фенибут",
                date = LocalDate.now(),
                time = LocalTime.of(18, 0)
            ),
            PillsScheduleData(
                name = "Фенозепам",
                date = LocalDate.now(),
                time = LocalTime.of(21, 0)
            ),
        )

        _atmPressure.value = 777
        _KRIndex.value = 6
        _temperature.value = 12
        _humidity.value = 81
    }
}