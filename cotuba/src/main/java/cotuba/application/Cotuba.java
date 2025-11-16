package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

/*
 * Classe criada a partir da página 61 eguindo o padrão MVC.
 * 
 *  Ela funciona como uma "controladora", coordenando o processo
 * principal do programa.
 *
 *Ela faz a conexao com o restante do código fazendo assim ela ser a model no padrão MVC
 * * deixando a classe Main mais simples e o código mais organizado.
 */

@Component
public class Cotuba {

	private final GeradorDePDF geradorDePDF;
	private final GeradorDeEPUB geradorDeEPUB;
	private final RenderizadorMDParaHTML renderizador;

	public Cotuba(GeradorDePDF geradorDePDF, GeradorDeEPUB geradorDeEPUB, RenderizadorMDParaHTML renderizador) {
		super();
		this.geradorDePDF = geradorDePDF;
		this.geradorDeEPUB = geradorDeEPUB;
		this.renderizador = renderizador;
	}

	public void executa(ParametrosCotuba parametros) {

		String formato = parametros.getFormato();
		Path diretorioDosMD = parametros.getDiretorioDosMD();
		Path arquivoDeSaida = parametros.getArquivoDeSaida();

		List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

		Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setArquivoDeSaida(arquivoDeSaida);
		ebook.setFormato(formato);
		ebook.setCapitulos(capitulos);

		if ("pdf".equals(formato)) {

			geradorDePDF.gera(ebook);

		} else if ("epub".equals(formato)) {
			geradorDeEPUB.gera(ebook);

		} else {
			throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
		}
	}
}
