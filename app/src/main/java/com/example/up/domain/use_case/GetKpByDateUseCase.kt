package com.example.up.domain.use_case

import android.util.Log
import com.example.up.common.Resource
import com.example.up.common.averageForDate
import com.example.up.domain.repository.KpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class GetKpByDateUseCase(
    private val kpRepository: KpRepository
) {
    suspend operator fun invoke(date: LocalDate): Flow<Resource<Float>> = kpRepository.getKpData().map { resource ->
        when (resource) {
            is Resource.Success -> {
                val avg = resource.data?.averageForDate(date = date) ?: 0.0f
                Log.d(
                    "Kp",
                    "$avg"
                )
                Resource.Success(
                    avg.toFloat()
                )
            }
            is Resource.Error -> Resource.Error(resource.message ?: "", null)
            is Resource.Loading -> Resource.Loading()
        }
    }
}