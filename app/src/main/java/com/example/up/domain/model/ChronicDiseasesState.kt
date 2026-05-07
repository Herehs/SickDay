package com.example.up.domain.model

data class ChronicDiseasesState(
    val diseaseList: List<Disease>
)

data class Disease(
    var selected: Boolean = false,
    val name: String
)
