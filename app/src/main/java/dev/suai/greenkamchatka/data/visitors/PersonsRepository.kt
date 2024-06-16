package dev.suai.greenkamchatka.data.visitors

import kotlinx.coroutines.flow.Flow

interface VisitorsRepository {

    suspend fun getAll(): Flow<List<Visitor>>
    suspend fun get(id:Int): Flow<Visitor>
    suspend fun add(newVisitor: Visitor)
    suspend fun update(editVisitor: Visitor)
    suspend fun delete(deleteVisitor: Visitor)
}