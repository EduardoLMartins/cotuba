package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.pdf.GeradorPDF;

/*
 * Classe criada a partir da página 61 eguindo o padrão MVC.
 * 
 *  Ela funciona como uma "controladora", coordenando o processo
 * principal do programa.
 *
 *Ela faz a conexao com o restante do código fazendo assim ela ser a model no padrão MVC
 * * deixando a classe Main mais simples e o código mais organizado.
 */

public class Cotuba {

	public void executa(String formato, Path diretorioDosMD, Path arquivoDeSaida) {
		var renderizador = new RenderizadorMDParaHTML();
		List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setArquivoDeSaida(arquivoDeSaida);
		ebook.setFormato(formato);
		ebook.setCapitulos(capitulos);
		
		
		if ("pdf".equals(formato)) {
			
			var geradorPDF = new GeradorPDF();
			geradorPDF.gera(ebook);

		} else if ("epub".equals(formato)) {
			var geradorEPUB = new GeradorEPUB();
			geradorEPUB.gera(ebook);
			
		} else {
			throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
		}
	}
}
