package com.tralkamy.tatika.model

import com.tralkamy.tatika.data.entity.TimeEntity

data class Partida(
    val mandante: TimeEntity,
    val visitante: TimeEntity,
    var golsMandante: Int = 0,
    var golsVisitante: Int = 0
)