package com.tralkamy.tatika.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "times")
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val ligaId: Int,
    val ataque: Int,
    val meio: Int,
    val defesa: Int,
)