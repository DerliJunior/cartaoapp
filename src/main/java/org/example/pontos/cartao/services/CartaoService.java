package org.example.pontos.cartao.services;

import org.example.pontos.cartao.models.*;
import org.example.pontos.transacao.service.TransacaoService;
import org.example.pontos.transacao.models.Transacao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CartaoService {
    TransacaoService transacaoService;

    public CartaoService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public ArrayList<Transacao> consultarTransacoes() {
        try {
            return transacaoService.listarTranscoes();

        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao processar arquivo");
        }
    }

    public Integer consultarBonusCartao(int tipoCartao) {
        CartaoStrategy cartaoStrategy = new CartaoStrategy();

        var transacoes = this.consultarTransacoes();

        switch (tipoCartao) {
            case 1:
                cartaoStrategy.setValorBonus(new Silver(transacoes));
                break;
            case 2:
                cartaoStrategy.setValorBonus(new Gold(transacoes));
                break;
            case 3:
                cartaoStrategy.setValorBonus(new Platinum(transacoes));
                break;
            case 4:
                cartaoStrategy.setValorBonus(new Unlimited(transacoes));
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipo de cartão inválido.");

        }


        return cartaoStrategy.getValorBonus();
    }
}
