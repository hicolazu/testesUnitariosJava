package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocacaoServiceTest {

    private static LocacaoService service;
    private static Usuario usuario;
    private static Filme filme;

    @ClassRule
    public static ErrorCollector error;

    @ClassRule
    public static ExpectedException exception;

    @BeforeClass
    public static void initialize() {
        // Cenário
        service = new LocacaoService();
        usuario = new Usuario("UsuarioTeste");
        filme = new Filme("Filme Teste", 2, 5.0);

        error = new ErrorCollector();
        exception = ExpectedException.none();
    }

    @Test
    public void valorFilmeTest() throws FilmeSemEstoqueException, LocadoraException {
        addEstoque();
        // Ação
        Locacao resultado = service.alugarFilme(usuario, filme);

        // Verificação
        assertThat(resultado.getValor(), is(5.0));
    }

    @Test
    public void dataLocacaoTest() throws FilmeSemEstoqueException, LocadoraException {
        addEstoque();
        Locacao resultado = service.alugarFilme(usuario, filme);

        assertThat(isMesmaData(resultado.getDataLocacao(), new Date()), is(true));
    }

    // Forma "ELEGANTE" de tratar exceções. OBS: JUnit que tratará essa exceções declaradas
    @Test
    public void dataRetornoTest() throws FilmeSemEstoqueException, LocadoraException {
        addEstoque();
        Locacao resultado = service.alugarFilme(usuario, filme);

        assertThat(isMesmaData(resultado.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void filmeSemEstoqueTest() throws FilmeSemEstoqueException, LocadoraException {
        clearEstoque();
        Locacao resultado = service.alugarFilme(usuario, filme);

        // Também poderiamos usar a assertThrows, disponível apenas na versão 4.13
    }

    // Forma "ROBUSTA" de tratar exceções
    @Test
    public void usuarioVazioTest() throws FilmeSemEstoqueException {
        addEstoque();

        try {
            service.alugarFilme(null, filme);
            Assert.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    // Forma alternativa de tratar exceções: Utilizando ExpectedException
    @Test
    public void filmeVazioTest() throws FilmeSemEstoqueException, LocadoraException {
        addEstoque();

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        service.alugarFilme(usuario, null);
    }

    private void clearEstoque() {
        filme.setEstoque(0);
    }

    private void addEstoque() {
        filme.setEstoque(2);
    }
}
