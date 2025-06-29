package com.tralkamy.tatika.data.db.dao

import androidx.room.*
import com.tralkamy.tatika.data.entity.TemporadaEntity

@Dao
interface TemporadaDao {
    @Query("SELECT * FROM temporadas")
    suspend fun getAll(): List<TemporadaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(temporada: TemporadaEntity): Long

    @Delete
    suspend fun delete(temporada: TemporadaEntity)
}