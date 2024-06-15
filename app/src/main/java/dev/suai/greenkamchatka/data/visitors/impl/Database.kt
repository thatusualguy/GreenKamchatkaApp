package dev.suai.greenkamchatka.data.visitors.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.suai.greenkamchatka.data.visitors.Visitor

@Database(
    entities = [Visitor::class],
    version = 1
)
abstract class VisitorDatabase : RoomDatabase(){
    abstract val dao: VisitorDao
}
