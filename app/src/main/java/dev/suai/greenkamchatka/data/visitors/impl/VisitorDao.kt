package dev.suai.greenkamchatka.data.visitors.impl

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.suai.greenkamchatka.data.visitors.Visitor

@Dao
interface VisitorDao  {
    @Query("SELECT * FROM visitor")
    fun getAll(): List<Visitor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(visitor:Visitor)

    @Delete
    fun delete(visitor:Visitor)
}
