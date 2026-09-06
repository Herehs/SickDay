package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.common.toGraphData
import com.example.up.domain.model.GraphData
import com.example.up.domain.repository.KpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class GetGraphDataUseCase(
    private val repository: KpRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double,
        date: String
    ): Flow<Resource<GraphData>> = repository.getKpData().map { resource ->
        when (resource) {
            is Resource.Success -> {
                Resource.Success(
                    resource.data?.toGraphData(LocalDate.parse(date)) ?: GraphData(list = listOf(0f))
                )
            }
            is Resource.Error -> Resource.Error(resource.message ?: "", null)
            is Resource.Loading -> Resource.Loading()
        }
    }
}