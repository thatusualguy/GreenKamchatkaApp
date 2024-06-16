package dev.suai.greenkamchatka.data.visitors.impl

import dev.suai.greenkamchatka.data.visitors.Visitor
import dev.suai.greenkamchatka.data.visitors.VisitorsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VisitorsRepositoryLocal @Inject constructor(
    private val dao: VisitorDao,
) : VisitorsRepository {

    override suspend fun getAll(): Flow<List<Visitor>> {
        return dao.getAll()
    }

    override suspend fun get(id: Int): Flow<Visitor> {
        return dao.get(id)
    }

    override suspend fun add(newVisitor: Visitor) {
        withContext(IO) {
            dao.insert(newVisitor)
        }
    }

    override suspend fun update(editVisitor: Visitor) {
        withContext(IO) {
            dao.insert(editVisitor)
        }
    }

    override suspend fun delete(deleteVisitor: Visitor) {
        withContext(IO) {
            dao.delete(deleteVisitor)
        }
    }

}
