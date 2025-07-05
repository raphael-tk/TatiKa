package com.tralkamy.tatika.model

fun criarTimeFolga(): Time {
    return Time(
        id = -1,
        nome = "FOLGA",
        ataque = 0,
        meio = 0,
        defesa = 0,
        ligaId = -1,
        jogadores = emptyList(),
        goleiro = Goleiro(
            id = -1,
            nome = "FOLGA GK",
            posicao = "GOL",
            elasticidade = 0,
            reflexo = 0,
            manejo = 0,
            posicionamento = 0,
            chute = 0,
            velocidade = 0,
            timeId = -1
        )
    )
}

fun criarPartidaSimples(mandante: Time, visitante: Time, rodada: Int, temporada: Int): Partida {
    return Partida(
        id = 0,
        temporada = temporada,
        rodada = rodada,
        mandanteId = mandante.id,
        visitanteId = visitante.id,
        mandante = mandante,
        visitante = visitante
    )
}
