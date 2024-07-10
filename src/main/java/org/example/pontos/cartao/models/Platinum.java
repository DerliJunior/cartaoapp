package org.example.pontos.cartao.models;

import org.example.pontos.cartao.interfaces.ICartaoStrategy;
import org.example.pontos.transacao.models.Transacao;

import java.util.ArrayList;

public class Platinum implements ICartaoStrategy {
    private ArrayList<Transacao> transacoes;

    public Platinum(ArrayList<Transacao> transacoes) {
        this.transacoes = new ArrayList<>(transacoes);
    }

    @Override
    public Integer calcularBonus() {
        Double valorCompras = 0.0;

//  O Platinum 1 ponto pora cada 1 real em compras realizadas no credito (2)
        for(Transacao transacao: transacoes) {
            if(transacao.tipo() == 2) {
                valorCompras += transacao.valor();
            }
        }

        return (int)Math.ceil(valorCompras);
    }
}
