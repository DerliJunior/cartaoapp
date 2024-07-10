package org.example.pontos.cartao.models;

import org.example.pontos.cartao.interfaces.ICartaoStrategy;
import org.example.pontos.transacao.models.Transacao;

import java.util.ArrayList;

public class Silver implements ICartaoStrategy {
    private Double valorCompras;
    private ArrayList<Transacao> transacoes;

    public Silver(ArrayList<Transacao> transacoes) {
        this.valorCompras = 0.0;
        this.transacoes = new ArrayList<>(transacoes);
    }

    @Override
    public Integer calcularBonus() {

        for(Transacao transacao: transacoes) {
            System.out.println(transacao.valor());
//            O Silver dá 1 ponto a cada 5 reais gastos, apenas para compras feitas no débito-1
            if(transacao.tipo() == 1) {
                valorCompras += transacao.valor();
            }
        }
        return (int)Math.ceil(this.valorCompras / 5);
    }
}
