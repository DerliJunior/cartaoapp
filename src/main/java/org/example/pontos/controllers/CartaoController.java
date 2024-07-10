package org.example.pontos.controllers;

import org.example.pontos.cartao.services.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cartoes")
public class CartaoController {
    CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @ResponseBody
    @GetMapping(value = "/pontos")
    public ResponseEntity<Integer> consultarBonusCartao(@RequestParam int tipoCartao) {
        var pontos = cartaoService.consultarBonusCartao(tipoCartao);

        return ResponseEntity.ok(pontos);
    }

}
