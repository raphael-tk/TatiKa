package com.tralkamy.tatika.data.db.dao

import androidx.room.*
import com.tralkamy.tatika.data.entity.TimeEntity
import com.tralkamy.tatika.model.Time

@Dao
interface TimeDao {
    @Query("SELECT * FROM times WHERE ligaId = :ligaId")
    suspend fun getTimesByLiga(ligaId: Int): List<TimeEntity>

    @Query("SELECT * FROM times")
    suspend fun getAllTimes(): List<TimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(time: TimeEntity): Long
}