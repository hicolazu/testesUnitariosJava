package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {

		if (usuario == null)
			throw new LocadoraException("Usuario vazio");


		if (filme == null)
			throw new LocadoraException("Filme vazio");


		if (filme.getEstoque() == 0)
			throw new FilmeSemEstoqueException();

		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public static void main(String[] args) {
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("UsuarioTeste");
		Filme filme = new Filme("Filme Teste", 2, 5.0);

		//ação
		Locacao resultado = null;
		try {
			resultado = service.alugarFilme(usuario, filme);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//verificação
		System.out.println("*** Testes ***");
		System.out.println("Valor: " + (resultado.getValor() == 5.0));
		System.out.println("Data de Locação: " + DataUtils.isMesmaData(resultado.getDataLocacao(), new Date()));
		System.out.println("Data de retorno: " + DataUtils.isMesmaData(resultado.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

		// F I R S T -> FAST, INDEPENDENT, REPEATABLE, SELF-VERIFYING & TIMELY
	}
}