package com.tralkamy.tatika.data.db.dao

import androidx.room.*
import com.tralkamy.tatika.data.entity.LigaEntity

@Dao
interface LigaDao {
    @Query("SELECT * FROM ligas")
    suspend fun getAll(): List<LigaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(liga: LigaEntity): Long
}