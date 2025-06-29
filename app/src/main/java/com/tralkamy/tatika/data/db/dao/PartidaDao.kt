package com.tralkamy.tatika.data.db.dao

import androidx.room.*
import com.tralkamy.tatika.data.entity.PartidaEntity

@Dao
interface PartidaDao {
    @Query("SELECT * FROM partidas WHERE temporada = :temporada AND rodada = :rodada")
    suspend fun getRodada(temporada: Int, rodada: Int): List<PartidaEntity>

    @Query("SELECT * FROM partidas WHERE temporada = :temporada")
    suspend fun getTemporada(temporada: Int): List<PartidaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(partida: PartidaEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(partidas: List<PartidaEntity>)
}
