package dev.suai.greenkamchatka.data.zones

import dev.suai.greenkamchatka.data.Resource

interface ZonesRepository {
    suspend fun getZones(): List<Zone>
}