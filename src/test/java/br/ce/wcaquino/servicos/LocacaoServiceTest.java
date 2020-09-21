package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LocacaoServiceTest {

    @Test
    public void alugarFilmeTest() {
        //cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("UsuarioTeste");
        Filme filme = new Filme("Filme Teste", 2, 5.0);

        //ação
        Locacao resultado = service.alugarFilme(usuario, filme);

        //verificação
        Assert.assertEquals(5.0, resultado.getValor(), 0.0);
        Assert.assertTrue(DataUtils.isMesmaData(resultado.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(resultado.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
