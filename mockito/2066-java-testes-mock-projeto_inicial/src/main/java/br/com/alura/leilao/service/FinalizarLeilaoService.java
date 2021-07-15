package br.com.alura.leilao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;

@Service
public class FinalizarLeilaoService {

	private final LeilaoDao leiloes;
	private final EnviadorDeEmails mailer;

	@Autowired
	public FinalizarLeilaoService(LeilaoDao leiloes, EnviadorDeEmails mailer) {
		this.leiloes = leiloes;
		this.mailer = mailer;
	}

	public void finalizarLeiloesExpirados() {
		List<Leilao> expirados = leiloes.buscarLeiloesExpirados();
		expirados.forEach(leilao -> {
			Lance maiorLance = maiorLanceDadoNoLeilao(leilao);
			leilao.setLanceVencedor(maiorLance);
			leilao.fechar();
			leiloes.salvar(leilao);
			this.mailer.enviarEmailVencedorLeilao(maiorLance);
		});
	}

	private Lance maiorLanceDadoNoLeilao(Leilao leilao) {
		List<Lance> lancesDoLeilao = leilao.getLances();
		Lance lanceMaximo = lancesDoLeilao.get(0);
		for (Lance lance : lancesDoLeilao)
			if (lance.getValor().compareTo(lanceMaximo.getValor()) > 0)
				lanceMaximo = lance;
		return lanceMaximo;
	}
	
}
