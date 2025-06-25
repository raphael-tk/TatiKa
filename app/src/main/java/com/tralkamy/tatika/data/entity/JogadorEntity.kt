package com.tralkamy.tatika.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jogadores")
data class JogadorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val posicao: String,
    val overall: Int,
    val timeId: Int
)