package com.tralkamy.tatika.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estatisticas_jogadores")
data class EstatisticaJogadorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val jogadorId: Int,
    val temporadaId: Int,
    val partidas: Int,
    val gols: Int,
    val assistencias: Int,
    val cartoesAmarelos: Int,
    val cartoesVermelhos: Int
)