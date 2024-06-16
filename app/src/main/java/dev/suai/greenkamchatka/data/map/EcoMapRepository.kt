package dev.suai.greenkamchatka.data.map

import kotlinx.coroutines.flow.Flow

interface EcoMapRepository {
    fun getAll(): Flow<List<ReportMarker>>
}