package org.example.pontos.cartao.models;

import org.example.pontos.cartao.interfaces.ICartaoStrategy;
import org.example.pontos.transacao.models.Transacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CartaoStrategy {
    private int valorBonus = 0;

    public void setValorBonus(ICartaoStrategy cartaoStrategy){
        valorBonus = cartaoStrategy.calcularBonus();
    }

    public int getValorBonus() {
        return this.valorBonus;
    }
}
