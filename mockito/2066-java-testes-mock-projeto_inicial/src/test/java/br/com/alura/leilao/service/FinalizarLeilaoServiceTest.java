package br.com.alura.leilao.service;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.utils.LeilaoCreator;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FinalizarLeilaoServiceTest {

    private FinalizarLeilaoService service;
    @Mock
    private LeilaoDao daoMock;
    @Mock
    private EnviadorDeEmails mailerMock;

    @BeforeEach
    private void initializeService() {
        // ler e inicializar anotacoes do mockito na classe atual
        MockitoAnnotations.initMocks(this);
        service = new FinalizarLeilaoService(daoMock, mailerMock);
    }

    @Test
    void deveFinalizarUmLeilao() {
        // cria lista de leiloes
        List<Leilao> leiloes = LeilaoCreator.createLeiloes();
        // define ao mockito que o metodo buscarLeiloesExpirados, quando chamado do mock, deve retornar a lista criada
        Mockito.when(daoMock.buscarLeiloesExpirados()).thenReturn(leiloes);

        // chamada do metodo a partir do service mockado
        service.finalizarLeiloesExpirados();
        Leilao leilao = leiloes.get(0);

        // verifica que o leilao foi fechado e que o maior lance foi definido como vencedor
        assertTrue(leilao.isFechado());
        assertEquals(new BigDecimal("8"), leilao.getLanceVencedor().getValor());

        Mockito.verify(daoMock).salvar(leilao);
    }

    @Test
    void deveEnviarEmailParaVencedorDoLeilao() {
        List<Leilao> leiloes = LeilaoCreator.createLeiloes();
        Mockito.when(daoMock.buscarLeiloesExpirados()).thenReturn(leiloes);
        service.finalizarLeiloesExpirados();

        Leilao leilao = leiloes.get(0);
        Lance lanceVencedor = leilao.getLanceVencedor();

        Mockito.verify(mailerMock).enviarEmailVencedorLeilao(lanceVencedor);
    }

    @Test
    void naoDeve_enviarEmailParaVencedor_seOcorrerFalhaAoSalvarLeilao() {
        List<Leilao> leiloes = LeilaoCreator.createLeiloes();

        Mockito.when(daoMock.salvar(Mockito.any())).thenThrow(RuntimeException.class);

        Mockito.verifyNoInteractions(mailerMock);
    }
}
