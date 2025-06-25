package com.tralkamy.tatika.data.db.dao

import androidx.room.*
import com.tralkamy.tatika.data.entity.JogadorEntity

@Dao
interface JogadorDao {
    @Query("SELECT * FROM jogadores WHERE timeId = :timeId")
    suspend fun getByTimeId(timeId: Int): List<JogadorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jogador: JogadorEntity): Long
}
