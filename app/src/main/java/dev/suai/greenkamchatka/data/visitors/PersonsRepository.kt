package dev.suai.greenkamchatka.data.visitors

interface VisitorsRepository {
    suspend fun getAll(): List<Visitor>
    suspend fun add(newVisitor: Visitor)
    suspend fun update(editVisitor: Visitor)
    suspend fun delete(deleteVisitor: Visitor)
}