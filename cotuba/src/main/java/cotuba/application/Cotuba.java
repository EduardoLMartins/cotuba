package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorDeEPUB;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.pdf.GeradorDePDF;

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
		RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
		List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setArquivoDeSaida(arquivoDeSaida);
		ebook.setFormato(formato);
		ebook.setCapitulos(capitulos);
		
		
		if ("pdf".equals(formato)) {
			
			GeradorDePDF geradorPDF = GeradorDePDF.cria();
			geradorPDF.gera(ebook);

		} else if ("epub".equals(formato)) {
			GeradorDeEPUB geradorEPUB = GeradorDeEPUB.cria();
			geradorEPUB.gera(ebook);
			
		} else {
			throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
		}
	}
}
