package org.example.pontos.cartao.models;

import org.example.pontos.cartao.interfaces.ICartaoStrategy;
import org.example.pontos.transacao.models.Transacao;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Unlimited implements ICartaoStrategy {
    private ArrayList<Transacao> transacoes;
    Double valorCompras = 0.0;

    public Unlimited(ArrayList<Transacao> transacoes) {
        this.transacoes = new ArrayList<>(transacoes);
    }

    @Override
    public Integer calcularBonus() {
//  * Unlimited 2 ponto para cada 1 real em compras realizadas nos ultimos 90 dias.
        for (Transacao transacao : transacoes) {

            if (transacao.dataHora()
                    .isAfter(LocalDateTime.now()
                            .minusDays(90))) {

                valorCompras += transacao.valor();
            }
        }

        return this.valorCompras.intValue() * 2;
    }
}
