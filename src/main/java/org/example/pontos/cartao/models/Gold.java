package org.example.pontos.cartao.models;

import org.example.pontos.cartao.interfaces.ICartaoStrategy;
import org.example.pontos.transacao.models.Transacao;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Gold implements ICartaoStrategy {
    private Double valorCompras = 0.0;
    private ArrayList<Transacao> transacoes;

    public Gold(ArrayList<Transacao> transacoes) {
        this.transacoes = new ArrayList<>(transacoes);
    }

    @Override
    public Integer calcularBonus() {

//  * O Gold dá 2 pontos a cada 8 reais para as compras realizdas nos utlimos 20 dias (débito -1 ou Credito - 2)
        for (Transacao transacao : transacoes) {
            if (transacao.dataHora()
                    .isAfter(LocalDateTime.now()
                            .minusDays(20))) {

                this.valorCompras += transacao.valor();
            }

        }
        return (int)Math.ceil((this.valorCompras / 8) * 2);
    }
}
