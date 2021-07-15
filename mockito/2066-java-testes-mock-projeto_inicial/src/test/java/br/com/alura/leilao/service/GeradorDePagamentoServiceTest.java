package br.com.alura.leilao.service;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.utils.LeilaoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GeradorDePagamentoServiceTest {

    private GeradorDePagamento geradorDePagamentoMock;

    @Mock
    private PagamentoDao daoMock;

    @Captor
    private ArgumentCaptor<Pagamento> pagamentoCaptor;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.initMocks(this);
        geradorDePagamentoMock = new GeradorDePagamento(daoMock);
    }

    @Test
    void deveriaCriarPagamentoParaVencedorDoLeilao() {
        Leilao leilao = LeilaoCreator.createLeilao();
        Lance vencedor = leilao.getLanceVencedor();
        geradorDePagamentoMock.gerarPagamento(vencedor);

        // o captor consegue capturar o parametro passado dentro de uma classe mocada
        Mockito.verify(daoMock).salvar(pagamentoCaptor.capture());

        Pagamento pagamento = pagamentoCaptor.getValue();

        assertEquals(LocalDate.now().plusDays(1), pagamento.getVencimento());
        assertEquals(vencedor.getValor(), pagamento.getValor());
        assertFalse(pagamento.getPago());
        assertEquals(vencedor.getUsuario(), pagamento.getUsuario());
        assertEquals(leilao, pagamento.getLeilao());
    }
}
