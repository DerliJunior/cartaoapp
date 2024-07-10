package org.example.pontos.transacao.service;

import org.example.pontos.transacao.models.Transacao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public class TransacaoService {
    @Value("${path.transacao-file}")
    private String extratoFilePath;


    public ArrayList<Transacao> listarTranscoes() throws IOException {
        ArrayList<Transacao> transacoes = new ArrayList<>();

        FileReader reader = null;
        BufferedReader br = null;

        try {
            File file = new File(extratoFilePath);
            reader = new FileReader(file);
            br = new BufferedReader(reader);

            String linha = null;

            while ((linha = br.readLine()) != null) {
                Transacao transacao = linhaObjeto(linha);
                transacoes.add(transacao);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao processar arquivo");
        } finally {
            reader.close();
            br.close();
        }

        return transacoes;
    }

    static Transacao linhaObjeto(String linha) {
        String[] movimentacao = linha.split(";");
        String desc = movimentacao[0];
        Integer tipo = Integer.parseInt(movimentacao[1]);
        double valor = Double.parseDouble(movimentacao[2]);
        LocalDateTime dataHora = LocalDateTime.parse(movimentacao[3]);

        return new Transacao(desc, tipo, valor, dataHora);
    }
}
