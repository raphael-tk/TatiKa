package com.tralkamy.tatika.data.db.dao

import androidx.room.*
import com.tralkamy.tatika.data.entity.EstatisticaJogadorEntity

@Dao
interface EstatisticaJogadorDao {
    @Query("SELECT * FROM estatisticas_jogadores WHERE jogadorId = :jogadorId")
    suspend fun getByJogador(jogadorId: Int): List<EstatisticaJogadorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estad: EstatisticaJogadorEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lista: List<EstatisticaJogadorEntity>)

    @Delete
    suspend fun delete(estad: EstatisticaJogadorEntity)
}