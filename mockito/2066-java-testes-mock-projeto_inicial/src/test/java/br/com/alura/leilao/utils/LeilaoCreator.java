package br.com.alura.leilao.utils;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LeilaoCreator {
    public static List<Leilao> createLeiloes() {
        List<Leilao> leiloes = new ArrayList<>();

        Leilao leilao = new Leilao("Produto", new BigDecimal("10"), new Usuario("user"));
        Lance firstLance = new Lance(new Usuario("proposer"), new BigDecimal("8"));
        Lance secondLance = new Lance(new Usuario("proposer"), new BigDecimal("7"));
        leilao.propoe(firstLance);
        leilao.propoe(secondLance);
        leiloes.add(leilao);

        return leiloes;
    }

    public static Leilao createLeilao() {
        Leilao leilao = new Leilao("produto", new BigDecimal("100"), new Usuario("user"));
        leilao.propoe(new Lance(new Usuario("proposer"), new BigDecimal("90")));
        leilao.setLanceVencedor(leilao.getLances().get(0));
        return leilao;
    }
}
