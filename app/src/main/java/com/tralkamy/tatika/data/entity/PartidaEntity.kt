package com.tralkamy.tatika.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partidas")
data class PartidaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val temporada: Int,
    val rodada: Int,
    val mandanteId: Int,
    val visitanteId: Int,
    val golsMandante: Int,
    val golsVisitante: Int
)
