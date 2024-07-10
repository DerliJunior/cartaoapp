package org.example.pontos.transacao.models;

import java.time.LocalDateTime;

public record Transacao(String descricao, Integer tipo, Double valor, LocalDateTime dataHora){
}